package palindrome

import java.util

object ScalaMainClass {
  def main(args: Array[String]): Unit = {
    println(isPalindromePermutation("Hello world!"))
    println(isPalindromePermutation("live evil"))
  }

  def isPalindromePermutation(str: String): Boolean = {
    val charWithCountMap = indexStrByCharCount(str)
    if (str.length % 2 == 0) checkEvenMap(charWithCountMap)
    else checkOddMap(charWithCountMap)
  }

  private def checkOddMap(charWithCountMap: Map[Char, Int]): Boolean = {
    var numOfOddChars: Int = 0
    for (entry <- charWithCountMap) {
      if (entry._2 % 2 != 0) numOfOddChars += 1
      if (numOfOddChars > 1) return false
    }
    numOfOddChars == 1
  }

  private def checkEvenMap(charWithCountMap: Map[Char, Int]): Boolean =
    charWithCountMap.values.forall(_ % 2 == 0)

  private def indexStrByCharCount(str: String): Map[Char, Int] = {
    str.toCharArray.groupBy(identity).map(entry => entry._1 -> entry._2.length)
  }
}