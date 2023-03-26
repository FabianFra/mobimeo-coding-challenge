package com.ffrank.mobimeo
package util

object AppProperty {

  val ROOT_PATH: String = new java.io.File(".").getAbsolutePath

  val RESOURCES_PATH: String = ROOT_PATH.concat("/src/main/scala/resources")

  val LINE_CSV_PATH: String = AppProperty.RESOURCES_PATH.concat("/lines.csv")

  val DELAYS_CSV_PATH: String = AppProperty.RESOURCES_PATH.concat("/delays.csv")

  val STOP_CSV_PATH: String = AppProperty.RESOURCES_PATH.concat("/stops.csv")

  val TIMES_CSV_PATH: String = AppProperty.RESOURCES_PATH.concat("/times.csv")
}
