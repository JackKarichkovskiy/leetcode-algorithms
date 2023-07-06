package factorial

import java.util
import scala.util.control.Breaks.break

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    println("recursive result: " + factorialRec(10))
//    println("iterative result: " + factorialIter(10))
  }

  def factorialRec(value: Int): Int = {
    if (value <= 1) return 1
    value * factorialRec(value - 1)
  }

  def factorialIter(value: Int): Int = {
    val stack: util.Stack[Integer] = new util.Stack[Integer]
    stack.add(value)
    var result: Int = 1
    while (!stack.isEmpty) {
      val nextElem: Integer = stack.pop
      if (nextElem <= 1) break //todo: break is not supported
      result *= nextElem
      stack.add(nextElem - 1)
    }
    result
  }

}
