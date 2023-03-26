package com.ffrank.mobimeo
package util

import model.{Line, Stop, Time}
import repository.{LineRepository, StopRepository}

object CsvHandler {

  def readCsv(csvPath: String, hasHeader: Boolean = true): Iterator[String] = {
    val file = io.Source.fromFile(csvPath)

    if (hasHeader) file.getLines().drop(1)
    else file.getLines()
  }

  def constructStops(csvPath: String, delimiter: String = ","): Set[Stop] = {
    val lines = CsvHandler.readCsv(csvPath)

    var stopObjects: Set[Stop] = Set()

    for (line <- lines) {
      if (!line.isBlank) {
        val Array(stopId, x, y) = line.split(delimiter).map(_.trim)

        stopObjects += Stop(stopId.toLong, x.toLong, y.toLong)
      }
    }

    stopObjects
  }

  def constructTimes(csvPath: String, delimiter: String = ","): Set[Time] = {
    val lines = CsvHandler.readCsv(csvPath)

    var timeObjects: Set[Time] = Set()

    for (line <- lines) {
      if (!line.isBlank) {
        val Array(lineId, stopId, timeString) = line.split(delimiter).map(_.trim)

        val matchingLine = LineRepository.findById(lineId.toLong).getOrElse(throw new RuntimeException(s"No line found for id $lineId"))
        val matchingStop = StopRepository.findById(stopId.toLong).getOrElse(throw new RuntimeException(s"No stop found for id $stopId"))

        timeObjects += Time(DateTimeHandler.toDateTime(timeString), matchingLine, matchingStop)
      }
    }

    timeObjects
  }

  /**
   * Reads csv file and maps the data to Line objects.
   *
   * @return Set of Line objects
   */
  def constructLines(csvPath: String, delayCsvPath: String, delimiter: String = ","): Set[Line] = {
    val lines = CsvHandler.readCsv(csvPath)

    var lineObjects: Set[Line] = Set()
    val delays = constructDelayMap(delayCsvPath)

    for (line <- lines) {
      if (!line.isBlank) {
        val Array(lineId, name) = line.split(delimiter).map(_.trim)

        val staticDelay: Int =
          if (delays.contains(name)) delays(name)
          else 0

        lineObjects += Line(lineId.toLong, name, staticDelay)
      }
    }

    lineObjects
  }

  /**
   * Reads csv file and returns the data as a map (key=Name of Line as String, value = static delay of Line as Int)
   *
   * @return Set of Line objects
   */
  def constructDelayMap(csvPath: String, delimiter: String = ","): Map[String, Int] = {
    val lines = CsvHandler.readCsv(csvPath)

    var delays: Map[String, Int] = Map()

    for (line <- lines) {
      val Array(lineName, delay) = line.split(delimiter).map(_.trim)

      delays += (lineName -> delay.toInt)
    }

    delays
  }
}
