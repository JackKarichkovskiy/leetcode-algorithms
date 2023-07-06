package listofdepths

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val tree = new ScalaTree(ValueNode(10,
      ValueNode(5,
        ValueNode(3,
          NoneNode,
          ValueNode(4, NoneNode, NoneNode)
        ),
        NoneNode
      ),
      ValueNode(20,
        ValueNode(15,
          ValueNode(14, NoneNode, NoneNode),
          NoneNode
        ),
        ValueNode(30,
          ValueNode(25,
            NoneNode,
            ValueNode(26, NoneNode, NoneNode)
          ),
          NoneNode
        )
      )
    ))

    depthList(tree).valuesIterator.foreach(println)
  }

  def depthList(tree: ScalaTree): Map[Int, List[Int]] = {
    def traverse(node: TreeNode, level: Int, result: Map[Int, List[Int]]): Map[Int, List[Int]] = {
      node match {
        case NoneNode => result
        case ValueNode(value, left, right) =>
          val levelList = value :: result(level)
          val resultWithNextElem: Map[Int, List[Int]] = result.updated(level, levelList)
          val mapAfterLeftTraverse = traverse(left, level + 1, resultWithNextElem)
          traverse(right, level + 1, mapAfterLeftTraverse)
      }
    }
    val accumulator = Map[Int, List[Int]]().withDefault(_ => List())
    traverse(tree.root, 0, accumulator)
  }
}

class ScalaTree(val root: TreeNode) {
}

sealed trait TreeNode

case class ValueNode(value: Int, left: TreeNode, right: TreeNode) extends TreeNode {
  override def toString: String = Integer.toString(value)
}

case object NoneNode extends TreeNode
