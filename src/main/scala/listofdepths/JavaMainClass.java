package listofdepths;

import java.util.*;

public class JavaMainClass {

    public static void main(String[] args) {
        Tree tree = new Tree(new Node(10,
                new Node(5, new Node(
                        3, null, new Node(4, null, null)
                ), null),
                new Node(20,
                        new Node(15,
                                new Node(14, null, null),
                                null),
                        new Node(30,
                                new Node(25, null, new Node(26, null, null)),
                                null))
                ));
        Collection<LinkedList<Integer>> result = depthList(tree);

        for(LinkedList<Integer> list: result) {
            System.out.println(list);
        }
    }

    public static Collection<LinkedList<Integer>> depthList(Tree tree) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        traverse(tree.root, map, 0);
        return map.values();
    }

    private static void traverse(Node node, Map<Integer, LinkedList<Integer>> map, int level){
        if(node == null) return;

        LinkedList<Integer> levelList = map.computeIfAbsent(level, value -> new LinkedList<>());
        levelList.add(node.value);

        traverse(node.left, map, level + 1);
        traverse(node.right, map, level + 1);
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