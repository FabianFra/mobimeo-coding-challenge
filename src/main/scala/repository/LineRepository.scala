package com.ffrank.mobimeo
package repository

import model.{Line, Time}
import util.{CsvHandler, DateTimeHandler}

import org.joda.time.DateTime

/**
 * Object which holds all available lines.
 * Additionally the delays.csv will be read and mapped to the corresponding line objects.
 */
object LineRepository {

  private val LINE_CSV_PATH = getClass.getResource(".").getPath + "../resources/lines.csv"

  private val DELAYS_CSV_PATH = getClass.getResource(".").getPath + "../resources/delays.csv"

  val AVAILABLE_LINES: Set[Line] = CsvHandler.constructLines(LINE_CSV_PATH, DELAYS_CSV_PATH)


  def findById(id: Long): Option[Line] = AVAILABLE_LINES.find(_.id == id)
}
