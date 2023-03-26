package com.ffrank.mobimeo
package model

import org.joda.time.DateTime

/**
 * Entity objects for Time data. If the date would be stored in a relational data base, it would be an association table.
 *
 * @param time DateTime
 * @param lineObj corresponding Line object
 * @param stopObj corresponding Stop object
 */
final case class Time(time: DateTime, lineObj: Line, stopObj: Stop)
