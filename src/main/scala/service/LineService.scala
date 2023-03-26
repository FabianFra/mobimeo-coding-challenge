package com.ffrank.mobimeo
package service

import model.{Line, LineDelayed, Stop, Time}
import repository.{LineRepository, StopRepository, TimeRepository}
import util.DateTimeHandler

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import org.joda.time.DateTime
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
 * Trait for JSON marshalling of needed DTOs
 */
trait LineJsonProtocol extends DefaultJsonProtocol {
  implicit val lineFormat: RootJsonFormat[Line] = jsonFormat3(Line)
  implicit val lineDelayedFormat: RootJsonFormat[LineDelayed] = jsonFormat3(LineDelayed)
}

/**
 * Class which holds business logic of the available endpoints.
 */
object LineService extends LineJsonProtocol with SprayJsonSupport {

  implicit val lineMarshaller: spray.json.RootJsonFormat[Line] = jsonFormat3(Line.apply)

  implicit val lineDelayedMarshaller: spray.json.RootJsonFormat[LineDelayed] = jsonFormat3(LineDelayed.apply)

  /**
   * - Find a vehicle for a given time and X & Y coordinates
   * - Return the vehicle arriving next at a given stop
   * - Indicate if a given line is currently delayed
   */

  /**
   * Finds a vehicle for a given time and X & Y coordinates
   *
   * @param time Time with format `HH:MM:SS`
   * @param x X position
   * @param y Y position
   * @return StandardRoute
   */
  def findVehicleByTimeAndPosition(time: String, x: String, y: String): StandardRoute = {
    if (!time.matches(DateTimeHandler.DATE_REGEX)) {
      complete(HttpResponse(StatusCodes.BadRequest, entity = s"Time '$time' does not conform to ${DateTimeHandler.DATE_REGEX}"))
    } else if (!x.forall(Character.isDigit)) {
      complete(HttpResponse(StatusCodes.BadRequest, entity = s"Provided x is not a valid integer ($x)"))
    } else if (!y.forall(Character.isDigit)) {
      complete(HttpResponse(StatusCodes.BadRequest, entity = s"Provided y is not a valid integer ($y)"))
    } else {
      val vehicles: Set[Line] = timesToVehicles(TimeRepository.findAllByTimeAndPosition(time, x.toLong, y.toLong))

      if (vehicles.nonEmpty) complete(vehicles)
      else complete(HttpResponse(StatusCodes.NotFound, entity = s"No vehicles found for time $time and position ($x, $y)"))
    }
  }

  /**
   * Return the vehicle arriving next at a given stop
   *
   * @param stopId Stop object's id as context
   * @return StandardRoute
   */
  def findNextArrivingVehicle(stopId: Long): StandardRoute = {
    val stop: Option[Stop] = StopRepository.findById(stopId)

    if (stop.isEmpty) {
      return complete(HttpResponse(StatusCodes.NotFound, entity = s"No stop found for given id $stopId)"))
    }

    val times = TimeRepository.findTimesByStop(stop.get)

    if (times.isEmpty) { // Should not be the case due to our given date but may be important in real scenario
      println("ERROR: Inconsistent data regarding stop and time relationship")
      complete(HttpResponse(StatusCodes.InternalServerError))
    } else complete(findNextArrivingVehicle(times))
  }

  /**
   * Indicates if a given line is currently delayed
   *
   * @param lineId Line object's id as context
   * @return StandardRoute
   */
  def isVehicleDelayed(lineId: Long): StandardRoute = {
    val vehicle = LineRepository.findById(lineId)

    if (vehicle.nonEmpty) complete(LineDelayed(vehicle.get.id, vehicle.get.name, vehicle.get.delay > 0))
    else complete(HttpResponse(StatusCodes.NotFound, entity = s"No vehicle found for given id $lineId"))
  }

  private def timesToVehicles(times: Set[Time]): Set[Line] = times.map(_.lineObj)

  private def actualArrival(time: Time): DateTime = time.time.plusMinutes(time.lineObj.delay)

  /**
   * Finds the next arriving vehicle by the passed set of time objects
   *
   * @param times Set of time objects
   * @return Line object which arrive next
   */
  private def findNextArrivingVehicle(times: Set[Time]): Line = {
    var closestTime: Time = null

    times.foreach(t => {
      if (closestTime == null || actualArrival(t).isBefore(actualArrival(closestTime))) {
        closestTime = t
      }
    })

    closestTime.lineObj
  }
}
