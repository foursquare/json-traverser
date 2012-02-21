package com.foursquare.json.traverser

import net.liftweb.json.JValue

case class JsonError(reason: String, json: JValue)
