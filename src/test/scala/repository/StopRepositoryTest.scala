package com.ffrank.mobimeo
package repository

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Basic tests for the StopRepository
 */
class StopRepositoryTest {

  @Test
  def findById(): Unit = {
    val positiveTestOne: Long = 0
    val positiveTestTwo: Long = 11
    val negativeTest: Long = 12

    assertTrue(StopRepository.findById(positiveTestOne).nonEmpty)
    assertTrue(StopRepository.findById(positiveTestTwo).nonEmpty)
    assertTrue(StopRepository.findById(negativeTest).isEmpty)
  }
}
