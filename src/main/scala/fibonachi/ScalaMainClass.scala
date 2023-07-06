package fibonachi

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    println("recursive result: " + fibonachiRec(10))
  }

  def fibonachiRec(value: Int): Int = {
    if (value == 0) return 0
    if (value == 1) return 1
    fibonachiRec(value - 1) + fibonachiRec(value - 2)
  }
}
