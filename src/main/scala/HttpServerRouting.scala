package com.ffrank.mobimeo

import service.LineService

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object HttpServerRouting {

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "mobimeo-challenge")

  // needed for the future flatMap/onComplete in the end
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  /**
   * - Find a vehicle for a given time and X & Y coordinates
   * - Return the vehicle arriving next at a given stop
   * - Indicate if a given line is currently delayed
   */

  private val vehicleGetRoutes = get {
    concat(
      path("vehicle") {
        parameters("time", "x", "y") {
          (time, x, y) => LineService.findVehicleByTimeAndPosition(time, x, y)
        }
      },
      path("vehicle" / LongNumber / "is-delayed") {
        lineId => LineService.isVehicleDelayed(lineId)
      },
      path("vehicle" / "stop" / LongNumber) {
        stopId => LineService.findNextArrivingVehicle(stopId)
      }
    )
  }

  private val route = concat(vehicleGetRoutes)

  def main(args: Array[String]): Unit = {
    val bindingFuture = Http().newServerAt("localhost", 8081).bind(route)

    println(s"Server now online. Please navigate to http://localhost:8081/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
