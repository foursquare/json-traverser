package com.foursquare.json.traverser

import net.liftweb.json.JsonAST._

sealed class ResultType[T](pf: PartialFunction[JValue, T]) {
  val parse = pf.lift
}

object ResultType {
  implicit object String extends ResultType[String]({
    case JString(str) => str
  })

  implicit object Int extends ResultType[Int]({
    case JInt(bigInt) if bigInt.isValidInt => bigInt.intValue
  })

  implicit object Long extends ResultType[Long]({
    case JInt(bigInt) => bigInt.longValue
  })

  implicit object BigInt extends ResultType[BigInt]({
    case JInt(bigInt) => bigInt
  })

  implicit object Boolean extends ResultType[Boolean]({
    case JBool(bool) => bool
  })

  implicit object Null extends ResultType[Null]({
    case JNull => null
  })

  implicit object Double extends ResultType[Double]({
    case JDouble(n) => n
  })

  implicit object JObject extends ResultType[JObject]({
    case jobj: JObject => jobj
  })

  implicit object JArray extends ResultType[JArray]({
    case jarr: JArray => jarr
  })
}
