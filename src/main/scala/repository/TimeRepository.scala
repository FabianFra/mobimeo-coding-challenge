package com.ffrank.mobimeo
package repository

import model.{Stop, Time}
import util.{AppProperty, CsvHandler, DateTimeHandler}

import org.joda.time.DateTime

/**
 * Mocks a repository class for Time objects.
 *
 * Included functions are either convenience functions or are mocked db calls.
 */
object TimeRepository {

  private val AVAILABLE_TIMES: Set[Time] = CsvHandler.constructTimes(AppProperty.TIMES_CSV_PATH)

  def findAllByTimeAndPosition(dateTime: DateTime, x: Long, y: Long): Set[Time] = findAllByTime(dateTime).filter(hasMatchingPosition(_, x, y))

  def findAllByTimeAndPosition(timeString: String, x: Long, y: Long): Set[Time] = findAllByTimeAndPosition(DateTimeHandler.toDateTime(timeString), x, y)

  def findTimesByStop(stop: Stop): Set[Time] = TimeRepository.AVAILABLE_TIMES.filter(_.stopObj.equals(stop))

  private def findAllByTime(dateTime: DateTime): Set[Time] = TimeRepository.AVAILABLE_TIMES.filter(_.time.equals(dateTime))

  private def hasMatchingPosition(time: Time, x: Long, y: Long) = time.stopObj.x == x && time.stopObj.y == y
}
