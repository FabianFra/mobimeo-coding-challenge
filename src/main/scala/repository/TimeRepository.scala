package com.ffrank.mobimeo
package repository

import model.{Stop, Time}
import util.{CsvHandler, DateTimeHandler}

import org.joda.time.DateTime

object TimeRepository {

  private val TIMES_CSV_PATH = getClass.getResource(".").getPath + "../resources/times.csv"

  val AVAILABLE_TIMES: Set[Time] = CsvHandler.constructTimes(TIMES_CSV_PATH)

  def findAllByTimeAndPosition(dateTime: DateTime, x: Long, y: Long): Set[Time] = findAllByTime(dateTime).filter(hasMatchingPosition(_, x, y))

  def findAllByTimeAndPosition(timeString: String, x: Long, y: Long): Set[Time] = findAllByTimeAndPosition(DateTimeHandler.toDateTime(timeString), x, y)

  def findAllByTime(dateTime: DateTime): Set[Time] = TimeRepository.AVAILABLE_TIMES.filter(_.time.equals(dateTime))

  def findAllByTime(time: String): Set[Time] = findAllByTime(DateTimeHandler.toDateTime(time))

  def findTimesByStop(stop: Stop): Set[Time] = TimeRepository.AVAILABLE_TIMES.filter(_.stopObj.equals(stop))

  private def hasMatchingPosition(time: Time, x: Long, y: Long) = time.stopObj.x == x && time.stopObj.y == y
}
