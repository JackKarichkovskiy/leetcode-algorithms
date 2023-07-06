package pathswithsum;

import java.util.HashSet;
import java.util.Set;

public class JavaMainClass {

    public static void main(String[] args) {
        Tree tree = new Tree(new Node(10,
                new Node(5, new Node(
                        15, null, new Node(-5, new Node(5, null, null), null)
                ), null),
                new Node(20,
                        new Node(15,
                                new Node(14, null, null),
                                null),
                        new Node(30,
                                new Node(25, null, new Node(26, null, null)),
                                null))
        ));
        System.out.println(countAllPathsWithSum(tree, 30));
    }

    public static int countAllPathsWithSum(Tree tree, int sum) {
        return countPath(tree.root, Set.of(), sum);
    }

    private static int countPath(Node node, Set<Integer> sums, int sum) {
        if(node == null) return 0;

        Set<Integer> newSums = updateWithNewValue(sums, node.value);
        int count = 0;
        if(newSums.contains(sum)) count++;
        return count + countPath(node.left, newSums, sum) + countPath(node.right, newSums, sum);
    }

    private static Set<Integer> updateWithNewValue(Set<Integer> sums, int newValue) {
        Set<Integer> result = new HashSet<>(sums.size() + 1);
        for(int oldValue: sums) {
            result.add(oldValue + newValue);
        }
        result.add(newValue);
        return result;
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