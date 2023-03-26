package com.ffrank.mobimeo
package repository

import model.Line
import util.CsvHandler

/**
 * Mocks a repository class for Line objects.
 *
 * Included functions are either convenience functions or are mocked db calls.
 */
object LineRepository {

  private val LINE_CSV_PATH = getClass.getResource(".").getPath + "../resources/lines.csv"

  private val DELAYS_CSV_PATH = getClass.getResource(".").getPath + "../resources/delays.csv"

  val AVAILABLE_LINES: Set[Line] = CsvHandler.constructLines(LINE_CSV_PATH, DELAYS_CSV_PATH)

  def findById(id: Long): Option[Line] = AVAILABLE_LINES.find(_.id == id)
}
