package com.ffrank.mobimeo
package repository

import model.Line
import util.{AppProperty, CsvHandler}

/**
 * Mocks a repository class for Line objects.
 *
 * Included functions are either convenience functions or are mocked db calls.
 */
object LineRepository {

  private val AVAILABLE_LINES: Set[Line] = CsvHandler.constructLines(AppProperty.LINE_CSV_PATH, AppProperty.DELAYS_CSV_PATH)

  def findById(id: Long): Option[Line] = AVAILABLE_LINES.find(_.id == id)
}
