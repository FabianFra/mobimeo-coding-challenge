package com.ffrank.mobimeo
package repository

import model.Stop
import util.{AppProperty, CsvHandler}

/**
 * Mocks a repository class for Stop objects.
 *
 * Included functions are either convenience functions or are mocked db calls.
 */
object StopRepository {

  private val AVAILABLE_STOPS: Set[Stop] = CsvHandler.constructStops(AppProperty.STOP_CSV_PATH)

  def findById(id: Long): Option[Stop] = AVAILABLE_STOPS.find(_.id == id)
}
