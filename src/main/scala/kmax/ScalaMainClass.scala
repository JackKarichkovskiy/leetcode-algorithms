package kmax

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val array: Array[Int] = Array(2, 5, -1, 6, 10, 3, 2, 1)
    println(kthMax(array, 3))
    println(array.mkString("Array(", ", ", ")"))
  }

  def kthMax(array: Array[Int], k: Int): Int = {
    if (k > array.length) throw new RuntimeException("k > array.length")

    def findMax(start: Int): Int = {
      var maxIndex = start
      for (j <- start until array.length) {
        if (array(maxIndex) < array(j)) maxIndex = j
      }
      maxIndex
    }

    for (i <- 0 until k) {
      val maxIndex = findMax(i)
      val temp = array(maxIndex)
      array(maxIndex) = array(i)
      array(i) = temp
    }
    array(k - 1)
  }
}
