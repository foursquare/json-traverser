// package com.foursquare.json.traverser
// 
// import net.liftweb.json.JsonAST._
// 
// case class DynamicJsonTraverser(getTraverser: JsonTraverser) extends JsonTraverser with Dynamic {
//   def this(json: JValue) =
//     this(new JsonTraverserImpl(json))
// 
//   override def getJson: JValue =
//     getTraverser.getJson
// 
//   override def getError: Option[JsonError] =
//     getTraverser.getError
// 
//   override def getResult[T: ResultType]: Option[T] =
//     getTraverser.getResult[T]
// 
//   override def getIndex(index: Int): DynamicJsonTraverser =
//     DynamicJsonTraverser(getTraverser.getIndex(index))
// 
//   override def getKey(name: String): DynamicJsonTraverser =
//     DynamicJsonTraverser(getTraverser.getKey(name))
// 
//   def apply(index: Int): DynamicJsonTraverser =
//     getIndex(index)
// 
//   def applyDynamic(name: String)(indexes: Int*): DynamicJsonTraverser = {
//     if (indexes.isEmpty)
//       getKey(name)
//     else
//       getKey(name).getIndex(indexes.head)
//   }
// }
// 
// object DynamicJsonTraverser {
//   def apply(json: JValue): DynamicJsonTraverser = new DynamicJsonTraverser(json)
// }
