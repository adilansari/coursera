package com.twitter.sample

import org.codehaus.jackson._
import org.codehaus.jackson.JsonToken._

case class SimpleParsed(id: Long, text: String)

class SimpleParser {

  val parserFactory = new JsonFactory()

  def parse(str: String) = {
    val parser = parserFactory.createJsonParser(str)
    if (parser.nextToken() == START_OBJECT) {
      var token = parser.nextToken()
      var textOpt: Option[String] = None
      var idOpt: Option[Long] = None
      while (token != null) {
        if (token == FIELD_NAME) {
          parser.getCurrentName() match {
            case "text" => {
              parser.nextToken()
              textOpt = Some(parser.getText())
            }
            case "id" => {
              parser.nextToken()
              idOpt = Some(parser.getLongValue())
            }
            case _ => //noop
          }
        }
        token = parser.nextToken()
      }
      if (textOpt.isDefined && idOpt.isDefined) {
        Some(SimpleParsed(idOpt.get, textOpt.get))
      } else {
        None
      }
    } else {
      None
    }
  }
}
