package com.ffrank.mobimeo
package model

/**
 * Used as a DTO for the request checking if a line is delayed.
 *
 * @param lineId Id of the line
 * @param lineName Name of the line
 * @param delayed Flag if the line delayed
 */
final case class LineDelayed(lineId: Long, lineName: String, delayed: Boolean)
