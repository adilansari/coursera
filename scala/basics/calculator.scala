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
