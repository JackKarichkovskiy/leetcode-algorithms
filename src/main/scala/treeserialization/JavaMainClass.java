package treeserialization;

public class JavaMainClass {

    public static void main(String[] args) {
        Tree tree = new Tree(new Node(10,
                new Node(5, null, new Node(7, null, null)),
                new Node(20, null, null)
        ));

        String expected = "10,5,null,7,null,null,20,null,null";
        String result = serialize(tree);
        System.out.println(result);
        Tree deserialized = deserialize(result);
        System.out.println(serialize(deserialized));
    }

    public static String serialize(Tree tree) {
        return traverse(tree.root);
    }

    private static String traverse(Node node) {
        if(node == null) return "null";

        String root = String.valueOf(node.value);
        return root + "," + traverse(node.left) + "," + traverse(node.right);
    }

    public static Tree deserialize(String str) {
        String[] nodes = str.split(",");
        TraverseResult traverseResult = deserializeTraverse(nodes, 0);
        return new Tree(traverseResult.result);
    }

    private static TraverseResult deserializeTraverse(String[] nodes, int start) {
        String currStr = nodes[start];
        if(currStr.equals("null")) {
            return new TraverseResult(null, start + 1);
        }

        Integer currValue = Integer.valueOf(currStr);
        TraverseResult leftRes = deserializeTraverse(nodes, start + 1);
        TraverseResult rightRes = deserializeTraverse(nodes, leftRes.nextIndex);
        Node newNode = new Node(currValue, leftRes.result, rightRes.result);

        return new TraverseResult(newNode, rightRes.nextIndex);
    }
}
class TraverseResult {
    Node result;
    int nextIndex;

    public TraverseResult(Node result, int nextIndex) {
        this.result = result;
        this.nextIndex = nextIndex;
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
}