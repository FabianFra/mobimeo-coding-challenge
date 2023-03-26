package com.ffrank.mobimeo
package model

/**
 * Entity objects for Line data. The name is based on the lines.csv although the task refers to it as 'vehicle'.
 *
 * @param id Identifier of the object
 * @param name Name of the line
 * @param delay Delay of the line (data can be found in delays.csv)
 */
final case class Line(id: Long, name: String, delay: Int = 0)
