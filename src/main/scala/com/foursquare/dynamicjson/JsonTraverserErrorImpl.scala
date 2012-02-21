package com.foursquare.json.traverser

import net.liftweb.json.JsonAST.{JValue, JNothing}

private case class JsonTraverserErrorImpl(override val getError: Option[JsonError]) extends JsonTraverser {
  override def getJson: JValue = JNothing

  // We propagate only the first error
  override def apply(index: Int): JsonTraverser = this

  // We propagate only the first error
  override def apply(name: String): JsonTraverser = this

  override def getResult[T: ResultType]: Option[T] = None
}
