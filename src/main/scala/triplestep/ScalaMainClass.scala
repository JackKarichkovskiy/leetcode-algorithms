package triplestep

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    println(tripleStepCount(20))
  }

  def tripleStepCount(n: Int): Int = {
    val memo = new Array[Int](n)
    tripleStepCountRec(n, memo)
  }

  private def tripleStepCountRec(n: Int, memo: Array[Int]) = {
    getFromMemoOrCalculate(n - 1, memo) +
      getFromMemoOrCalculate(n - 2, memo) +
      getFromMemoOrCalculate(n - 3, memo)
  }

  private def getFromMemoOrCalculate(n: Int, memo: Array[Int]): Int = {
    if (n < 0) return 0
    if (n == 0) return 1
    if (memo(n - 1) != 0) return memo(n - 1)
    val calculated = tripleStepCountRec(n, memo)
    memo(n - 1) = calculated
    calculated
  }
}
