package example

/**
  * Created by Gunner on 6/26/16.
  */
object SqrtSession extends App {

  def sqrtIter(guess: Double, x: Double, steps: Int): Double = {
    if (isGoodEnough(guess, x)) steps
    else sqrtIter(improve(guess, x), x, steps + 1)
  }

  def isGoodEnough(guess: Double, x: Double) = {
    abs((Math.pow(guess, 2)) - x) < 0.01
  }

  def improve(guess: Double, x: Double): Double = {
    (guess + (x / guess)) / 2
  }

  def abs(x: Double) = {
    if (x < 0) -x else x
  }

  def sqrt(x: Double) = {
    println(sqrtIter(x/2, x, 1))
  }

  sqrt(4)
  sqrt(20)
  sqrt(576)
  sqrt(800)
  sqrt(900)
  sqrt(2056)
}
