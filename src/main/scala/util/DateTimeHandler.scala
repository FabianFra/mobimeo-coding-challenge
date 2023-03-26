package com.ffrank.mobimeo
package util

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Helper class to handle date time formatting
 */
object DateTimeHandler {

  private val DEFAULT_DATE_TIME_FORMATTER= DateTimeFormat.forPattern("HH:mm:ss")

  val DATE_REGEX = "[0-9]{2}:[0-9]{2}:[0-9]{2}"

  /**
   * Formats given time string to DateTime.
   *
   * @param timeString Time as string (static format is `HH:MM:SS`)
   * @return DateTime
   */
  def toDateTime(timeString: String): DateTime = DEFAULT_DATE_TIME_FORMATTER.parseDateTime(timeString)
}
