package com.twitter.sample

import org.scalatest._

class ArithmeticSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  var x = 0

  override def beforeAll() {
    println("lol before")
  }

  "Arithmetic" should "add two numbers" in {
    1 + 1 shouldBe 2
  }

  it should "add 3 numbers" in {
    2 + 4 + 6 shouldBe 12
  }

  override def afterAll() {
    println("after haha")
  }

  "Mutations are isolated" should "change value" in {
    x = 1
    x equals 1
  }

  it should "x should stay 1" in {
    x shouldEqual 1
  }

}

