package com.ffrank.mobimeo
package repository

import org.junit.Assert._
import org.junit.Test

/**
 * Basic tests for the LineRepository
 */
class LineRepositoryTest {

  @Test
  def findById(): Unit = {
    val positiveTest: Long = 1
    val negativeTest: Long = 99999

    assertTrue(LineRepository.findById(positiveTest).nonEmpty)
    assertTrue(LineRepository.findById(negativeTest).isEmpty)
  }
}
