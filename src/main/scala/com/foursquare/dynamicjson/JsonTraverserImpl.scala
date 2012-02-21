package com.foursquare.json.traverser

import net.liftweb.json.JsonAST.{JValue, JArray, JNothing}

private case class JsonTraverserImpl(override val getJson: JValue) extends JsonTraverser {
  override def getError: Option[JsonError] = None

  override def apply(index: Int): JsonTraverser = getJson match {
    case JArray(arr) if arr.size > index =>
      new JsonTraverserImpl(arr(index))

    case JArray(arr) =>
      val msg = "Attempt to access index " + index + " in array of length " + arr.size
      val error = JsonError(msg, getJson)
      new JsonTraverserErrorImpl(Some(error))

    case _ =>
      val error = JsonError("Json value is not an array", getJson)
      new JsonTraverserErrorImpl(Some(error))
  }

  override def apply(name: String): JsonTraverser = {
    val newJson = getJson \ name

    newJson match {
      case JNothing =>
        val error = JsonError("Attempt to access nonexistant field: " + name, getJson)
        new JsonTraverserErrorImpl(Some(error))

      case _ =>
        new JsonTraverserImpl(newJson)
    }
  }
}

