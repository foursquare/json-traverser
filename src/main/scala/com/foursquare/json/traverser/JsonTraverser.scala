package com.foursquare.json.traverser

import net.liftweb.json.JsonAST.JValue

trait JsonTraverser {
  def json: JValue
  def error: Option[JsonError]
  def result[T: ResultType]: Option[T] = {
    val resultType = implicitly[ResultType[T]]
    resultType.parse(json)
  }

  def apply(index: Int): JsonTraverser
  def apply(name: String): JsonTraverser
}

object JsonTraverser {
  def apply(json: JValue): JsonTraverser = new JsonTraverserImpl(json)
}
