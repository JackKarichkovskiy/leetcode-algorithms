package pathswithsum

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val tree = ScalaTree(ValueNode(10,
      ValueNode(5,
        ValueNode(15,
          NoneNode,
          ValueNode(-5,
            ValueNode(5, NoneNode, NoneNode),
            NoneNode
          )
        ),
        NoneNode
      ),
      ValueNode(20,
        ValueNode(15, ValueNode(14, NoneNode, NoneNode), NoneNode),
        ValueNode(30,
          ValueNode(25, NoneNode, ValueNode(26, NoneNode, NoneNode)),
          NoneNode))
    ))
    println(countAllPathsWithSum(tree, 30))
  }

  def countAllPathsWithSum(tree: ScalaTree, sum: Int): Int = countPath(tree.root, Set(), sum)

  private def countPath(node: TreeNode, sums: Set[Int], sum: Int): Int = {
    node match {
      case NoneNode => 0
      case ValueNode(value, left, right) =>
        val newSums = updateWithNewValue(sums, value)
        val countIfFound = if (newSums.contains(sum)) 1 else 0
        countIfFound + countPath(left, newSums, sum) + countPath(right, newSums, sum)
    }
  }

  private def updateWithNewValue(sums: Set[Int], newValue: Int): Set[Int] = {
    sums.map(_ + newValue) + newValue
  }
}

case class ScalaTree(root: TreeNode) {
}

sealed trait TreeNode

case class ValueNode(value: Int, left: TreeNode, right: TreeNode) extends TreeNode {
  override def toString: String = Integer.toString(value)
}

case object NoneNode extends TreeNode