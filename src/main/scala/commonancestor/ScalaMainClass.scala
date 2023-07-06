package commonancestor

object ScalaMainClass {

  def main(args: Array[String]): Unit = {
    val first = ValueNode(14, NoneNode, NoneNode)
    val second: ValueNode = ValueNode(25, NoneNode, ValueNode(26, NoneNode, NoneNode))
    val tree = ScalaTree(ValueNode(10,
      ValueNode(5,
        ValueNode(3, NoneNode, ValueNode(4, NoneNode, NoneNode)),
        NoneNode),
      ValueNode(20,
        ValueNode(15,
          first,
          NoneNode),
        ValueNode(30,
          second,
          NoneNode)
      )
    ))
    println(findCommonAncestor(tree, first, second))
  }

  def findCommonAncestor(tree: ScalaTree, first: ValueNode, second: ValueNode): TreeNode =
    checkPath(tree.root, first, second).commonAncestor

  private def checkPath(node: TreeNode, first: ValueNode, second: ValueNode): ScalaTraverseResult = {
    node match {
      case NoneNode => ScalaTraverseResult(firstFound = false, secondFound = false, NoneNode)
      case valueNode: ValueNode =>
        val traverseResult = traverseLeft(valueNode, first, second)
        traverseResult match {
          case leftResult: ScalaTraverseResult => leftResult
          case NotFoundResult =>
            val rightTraverseResult = traverseRight(valueNode, first, second)
            rightTraverseResult match {
              case rightResult: ScalaTraverseResult => rightResult
              case NotFoundResult => ScalaTraverseResult(first eq node, second eq node, NoneNode)
            }
        }
    }
  }

  private def traverseLeft(node: ValueNode, first: ValueNode, second: ValueNode): ScalaBaseTraverseResult = {
    val traverseResult = checkPath(node.left, first, second)
    if (traverseResult.bothFound) return traverseResult
    if (traverseResult.anyFound) {
      if (traverseResult.firstFound && (node eq second)) return ScalaTraverseResult(firstFound = true, secondFound = true, node)
      if (traverseResult.secondFound && (node eq first)) return ScalaTraverseResult(firstFound = true, secondFound = true, node)
      val rightTraverseResult = checkPath(node.right, first, second)
      if (traverseResult.firstFound && rightTraverseResult.secondFound) return ScalaTraverseResult(firstFound = true, secondFound = true, node)
      if (traverseResult.secondFound && rightTraverseResult.firstFound) return ScalaTraverseResult(firstFound = true, secondFound = true, node)
      return traverseResult
    }
    NotFoundResult
  }

  private def traverseRight(node: ValueNode, first: ValueNode, second: ValueNode): ScalaBaseTraverseResult = {
    val rightTraverseResult = checkPath(node.right, first, second)
    if (rightTraverseResult.bothFound) return rightTraverseResult
    if (rightTraverseResult.anyFound) {
      if (rightTraverseResult.firstFound && (node eq second)) return ScalaTraverseResult(firstFound = true, secondFound = true, node)
      if (rightTraverseResult.secondFound && (node eq first)) return ScalaTraverseResult(firstFound = true, secondFound = true, node)
      return rightTraverseResult
    }
    NotFoundResult
  }
}

sealed trait ScalaBaseTraverseResult

case class ScalaTraverseResult(firstFound: Boolean, secondFound: Boolean, commonAncestor: TreeNode) extends ScalaBaseTraverseResult {
  def bothFound: Boolean = firstFound && secondFound

  def anyFound: Boolean = firstFound || secondFound
}

case object NotFoundResult extends ScalaBaseTraverseResult

case class ScalaTree(root: TreeNode) {
}

sealed trait TreeNode

case class ValueNode(value: Int, left: TreeNode, right: TreeNode) extends TreeNode {
  override def toString: String = Integer.toString(value)
}

case object NoneNode extends TreeNode