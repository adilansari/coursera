package com.twitter.sample

import org.scalatest._

class SimpleParserSpec extends FlatSpec with Matchers {

  val parser = new SimpleParser()

  "SimpleParser" should "just work" in {
    val tweet = """{"id":1, "text":"foo"}"""
    parser.parse(tweet) match {
      case Some(parsed) => {
        parsed.id should equal(1)
      }
      case _ => fail("failed to parse tweet")
    }
  }
}

