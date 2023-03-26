package com.ffrank.mobimeo
package repository

import model.Time

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Basic tests for the TimeRepository
 */
class TimeRepositoryTest {

  /**
   * Test if all entities have been constructed properly.
   *
   * All Time objects need to have a time: String, stop: Stop and line: Line assigned to it.
   */
  @Test
  def checkEntities(): Unit = {
    val times: Set[Time] = TimeRepository.AVAILABLE_TIMES

    assertTrue(times.nonEmpty)

    times.foreach(o => {
      assertTrue(o.time != null && o.lineObj != null && o.stopObj != null)
    })
  }

}
