package com.ffrank.mobimeo
package util

import model.{Line, Stop, Time}

import org.junit.Assert.assertTrue
import org.junit.Test

class CsvHandlerTest {

  @Test
  def checkConstructLines(): Unit = {
    val lines: Set[Line] = CsvHandler.constructLines(AppProperty.LINE_CSV_PATH, AppProperty.DELAYS_CSV_PATH)

    assertNonEmpty(lines)
  }

  @Test
  def checkConstructStops(): Unit = {
    val stops: Set[Stop] = CsvHandler.constructStops(AppProperty.STOP_CSV_PATH)

    assertNonEmpty(stops)
  }

  @Test
  def checkConstructTimes(): Unit = {
    val times: Set[Time] = CsvHandler.constructTimes(AppProperty.TIMES_CSV_PATH)

    assertNonEmpty(times)
  }

  def assertNonEmpty(content: Set[_]): Unit = assertTrue(content.nonEmpty)
}
