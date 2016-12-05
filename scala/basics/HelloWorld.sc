class Calculator(brand: String) {
  val color : String = if (brand == "TI") {
    "blue"
  } else if (brand == "CA") {
    "red"
  } else {
    "white"
  }

  def add (m: Int, n: Int) : Int = m + n
}

class LogCalculator(brand: String) extends Calculator(brand) {
  def log(m: Int, base: Int): Double = math.log(m) / math.log(base)
  def log(m: Int): Double = log(m, 2)
}

val c = new LogCalculator("CA")
c.color
c.log(8)
c.log(8,3)

def bigger(o: Any): Any = {
  o match {
    case i: Int if i < 0 => i-1
    case i: Int => i + 1
    case d: Double if d < 0.0 => d + 0.1
    case d: Double => d + 0.1
    case text: String => text + "s"
  }
}

bigger(-5.1)


val arr = Array(1,2,3,4,5,6)
arr.length
arr(3)

val lis = List(1,2,3,4,5,6)
lis(4)


