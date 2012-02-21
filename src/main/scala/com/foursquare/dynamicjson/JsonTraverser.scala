package com.foursquare.json.traverser

import net.liftweb.json.JsonAST.JValue

trait JsonTraverser {
  def getJson: JValue
  def getError: Option[JsonError]
  def getResult[T: ResultType]: Option[T] = {
    val resultType = implicitly[ResultType[T]]
    resultType.parse(getJson)
  }

  // def getIndex(index: Int): JsonTraverser
  // def getKey(name: String): JsonTraverser
  def apply(index: Int): JsonTraverser
  def apply(name: String): JsonTraverser
}

object JsonTraverser {
  def apply(json: JValue): JsonTraverser = new JsonTraverserImpl(json)
}
