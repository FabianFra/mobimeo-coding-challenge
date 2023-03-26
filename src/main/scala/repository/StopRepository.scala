package com.ffrank.mobimeo
package repository

import model.Stop
import util.CsvHandler

object StopRepository {

  private val STOP_CSV_PATH = getClass.getResource(".").getPath + "../resources/stops.csv"

  val AVAILABLE_STOPS: Set[Stop] = CsvHandler.constructStops(STOP_CSV_PATH)

  def findById(id: Long): Option[Stop] = AVAILABLE_STOPS.find(_.id == id)
}
