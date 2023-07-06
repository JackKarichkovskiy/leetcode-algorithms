package commonancestor;

public class JavaMainClass {

    public static void main(String[] args) {
        Node first = new Node(100, null, null);
        Node second;
        Tree tree = new Tree(new Node(10,
                new Node(5, new Node(
                        3, null, new Node(4, null, null)
                ), null),
                new Node(20,
                        new Node(15,
                                new Node(14, null, null),
                                null),
                        new Node(30,
                                second = new Node(25, null, new Node(26, null, null)),
                                null))
        ));
        System.out.println(findCommonAncestor(tree, first, second));
    }

    public static Node findCommonAncestor(Tree tree, Node first, Node second) {
        return checkPath(tree.root, first, second).commonAncestor;
    }

    private static TraverseResult checkPath(Node node, Node first, Node second) {
        if(node == null) return new TraverseResult(false, false, null);

        TraverseResult traverseResult = traverseLeft(node, first, second);
        if (traverseResult != null) return traverseResult;

        TraverseResult rightTraverseResult = traverseRight(node, first, second);
        if (rightTraverseResult != null) return rightTraverseResult;

        return new TraverseResult(first == node, second == node, null);
    }

    private static TraverseResult traverseLeft(Node node, Node first, Node second) {
        TraverseResult traverseResult = checkPath(node.left, first, second);
        if(traverseResult.bothFound()) return traverseResult;
        if(traverseResult.anyFound()) {
            if(traverseResult.firstFound && node == second) return new TraverseResult(true, true, node);
            if(traverseResult.secondFound && node == first) return new TraverseResult(true, true, node);
            TraverseResult rightTraverseResult = checkPath(node.right, first, second);
            if(traverseResult.firstFound && rightTraverseResult.secondFound)
                return new TraverseResult(true, true, node);
            if(traverseResult.secondFound && rightTraverseResult.firstFound)
                return new TraverseResult(true, true, node);
            return traverseResult;
        }
        return null;
    }

    private static TraverseResult traverseRight(Node node, Node first, Node second) {
        TraverseResult rightTraverseResult = checkPath(node.right, first, second);
        if(rightTraverseResult.bothFound()) return rightTraverseResult;
        if(rightTraverseResult.anyFound()) {
            if (rightTraverseResult.firstFound && node == second) return new TraverseResult(true, true, node);
            if (rightTraverseResult.secondFound && node == first) return new TraverseResult(true, true, node);
            return rightTraverseResult;
        }
        return null;
    }
}

class TraverseResult {
    boolean firstFound;
    boolean secondFound;
    Node commonAncestor;

    public TraverseResult(boolean firstFound, boolean secondFound, Node commonAncestor) {
        this.firstFound = firstFound;
        this.secondFound = secondFound;
        this.commonAncestor = commonAncestor;
    }

    public boolean bothFound() {
        return firstFound && secondFound;
    }

    public boolean anyFound() {
        return firstFound || secondFound;
    }
}

class Tree {
    Node root;

    public Tree(Node root) {
        this.root = root;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}