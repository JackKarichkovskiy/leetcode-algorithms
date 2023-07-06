package bitinsertion

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val value = 10;
    println("value: " + Integer.toBinaryString(value));
    val insertValue = 5;
    println("insertValue: " + Integer.toBinaryString(insertValue));
    println("result: " + Integer.toBinaryString(insertion(value, insertValue, 0, 2)));
  }

  def insertion(value: Int, insertValue: Int, start: Int, end: Int): Int =
    clear(value, start, end) | (insertValue << start)

  def clear(value: Int, start: Int, end: Int): Int = {
    var onesInTheRightPlace = 1
    for (_ <- 0 until end - start) {
      onesInTheRightPlace <<= 1
      onesInTheRightPlace += 1
    }
    onesInTheRightPlace <<= start
    value & ~onesInTheRightPlace
  }
}
