package sortedmerge

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val arr = Vector("asd", "bsd", "dsa", "dsaz", "dsb")
    println(sortGroupedAnagrams(arr))
  }

  def sortGroupedAnagrams(arr: Vector[String]): Vector[String] = {
    case class ScalaListEntry(original: String, sorted: String)
    arr.map(str => ScalaListEntry(str, str.sorted)).sortBy(_.sorted).map(_.original)
  }
}
