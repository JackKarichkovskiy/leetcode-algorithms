package zeromatrix

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val input = Array(
      Array(1, 2, 3),
      Array(5, 5, 0),
      Array(6, 7, 8),
      Array(9, 9, 0)
    )
    zeroMatrix(input)
    println(input.map(_.mkString(",")) mkString("Array(\n", "\n", "\n)"))
  }

  def zeroMatrix(matrix: Array[Array[Int]]): Unit = {
    val zeroRowsBuilder = Set.newBuilder[Integer]
    val zeroColumnsBuilder = Set.newBuilder[Integer]
    for {
      i <- matrix.indices
      j <- matrix(i).indices
      if matrix(i)(j) == 0
    } {
      zeroRowsBuilder += i
      zeroColumnsBuilder += j
    }
    val zeroRows = zeroRowsBuilder.result
    val zeroColumns = zeroColumnsBuilder.result

    for {
      i <- zeroRows
      j <- zeroColumns
    } zerofy(matrix, i, j)
  }

  private def zerofy(matrix: Array[Array[Int]], row: Int, column: Int): Unit = {
    for (i <- matrix.indices) {
      matrix(i)(column) = 0
    }
    for (i <- matrix(row).indices) {
      matrix(row)(i) = 0
    }
  }
}
