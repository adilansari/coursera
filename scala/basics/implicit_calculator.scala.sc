import scala.annotation.implicitNotFound

object Calci {
  @implicitNotFound("Implementation not found error")
  trait NumberLike[T]{
    def plus(x: T, y: T): T
    def minus(x: T, y: T): T
    def divide(x: T, y: T): T
  }

  object NumberLike {
    implicit object NumberLikeInt extends NumberLike[Int]{
      override def plus(x: Int, y: Int): Int = x + y

      override def minus(x: Int, y: Int): Int = {
        if (x > y) x - y
        else y - x
      }

      override def divide(x: Int, y: Int): Int = x/y
    }

    implicit object NumberLikeDouble extends NumberLike[Double]{
      override def plus(x: Double, y: Double): Double = x + y

      override def minus(x: Double, y: Double): Double = {
        if (x > y) x - y
        else y - x
      }

      override def divide(x: Double, y: Double): Double = x/y
    }
  }
}


object Stat {
  import Calci.NumberLike
  def add[T](xs: Vector[T])(implicit ev: NumberLike[T]): T = {
    ev.plus(xs.head, xs.last)
  }
}

Stat.add(Vector[Int](1,3,5,7,9))