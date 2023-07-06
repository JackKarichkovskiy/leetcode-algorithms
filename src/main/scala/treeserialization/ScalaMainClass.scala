package treeserialization

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val tree = ScalaTree(ValueNode(10, ValueNode(5, NoneNode, ValueNode(7, NoneNode, NoneNode)), ValueNode(20, NoneNode, NoneNode)))

    val expected = "10,5,null,7,null,null,20,null,null"
    val result = serialize(tree)
    println(result)
    val deserialized = deserialize(result)
    println(serialize(deserialized))
  }

  def serialize(tree: ScalaTree): String = {
    def traverse(node: TreeNode): String = {
      node match {
        case NoneNode => "null"
        case ValueNode(value, left, right) =>
          val rootValue = value.toString
          s"$rootValue,${traverse(left)},${traverse(right)}"
      }
    }

    traverse(tree.root)
  }

  def deserialize(str: String): ScalaTree = {
    val nodes = str.split(",").toVector

    def traverse(start: Int): ScalaTraverseResult = {
      val currStr = nodes(start)
      if (currStr == "null") return ScalaTraverseResult(NoneNode, start + 1)
      val leftRes = traverse(start + 1)
      val rightRes = traverse(leftRes.nextIndex)
      val newNode = ValueNode(currStr.toInt, leftRes.result, rightRes.result)
      ScalaTraverseResult(newNode, rightRes.nextIndex)
    }

    ScalaTree(traverse(0).result)
  }
}

case class ScalaTraverseResult(result: TreeNode, nextIndex: Int) {
}

case class ScalaTree(root: TreeNode) {
}

sealed trait TreeNode

case class ValueNode(value: Int, left: TreeNode, right: TreeNode) extends TreeNode {
  override def toString: String = Integer.toString(value)
}

case object NoneNode extends TreeNode