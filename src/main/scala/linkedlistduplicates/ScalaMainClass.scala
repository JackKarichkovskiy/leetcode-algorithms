package linkedlistduplicates

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val lBuilder = List.newBuilder[Int]
    for (i <- 0 until 100) {
      lBuilder += i
      if (Math.random() > 0.5) lBuilder += i
    }
    val input = lBuilder.result
    println(input)
    val result = removeDuplicatesFor(input)
    println(result)
  }

  def removeDuplicatesFor(list: List[Int]): List[Int] = {
    def removeDuplicatesForRec(startingNode: List[Int], value: Int): List[Int] = {
      startingNode match {
        case Nil => startingNode
        case elem :: tail =>
          if (elem == value) removeDuplicatesForRec(tail, value)
          else elem :: removeDuplicatesForRec(tail, value)
      }
    }

    list match {
      case Nil => Nil
      case elem :: tail => elem :: removeDuplicatesFor(removeDuplicatesForRec(tail, elem))
    }
  }
}
