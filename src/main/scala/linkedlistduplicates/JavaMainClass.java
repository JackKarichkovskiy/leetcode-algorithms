package linkedlistduplicates;

public class JavaMainClass {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 100; i++) {
            list.addToHead(i);
            if (Math.random() > 0.5) {
                list.addToHead(i);
            }
        }
        System.out.println(list);
        removeDuplicates(list);
        System.out.println(list);
    }

    private static void removeDuplicates(LinkedList list) {
        if (list.head == null) {
            return;
        }
        Node currentNode = list.head;
        while (currentNode != null && currentNode.next != null) {
            removeDuplicatesFor(currentNode);
            currentNode = currentNode.next;
        }
    }

    private static void removeDuplicatesFor(Node startingNode) {
        int originalValue = startingNode.data;
        Node currentNode = startingNode;
        while (currentNode.next != null) {
            if (currentNode.next.data == originalValue) {
                currentNode.next = currentNode.next.next;
                continue;
            }
            currentNode = currentNode.next;
        }
    }
}

class LinkedList {
    Node head = null;

    public void addToHead(int data) {
        Node newHead = new Node(data);
        newHead.next = this.head;
        this.head = newHead;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node curr = head;
        while (curr.next != null) {
            stringBuilder.append(curr.data).append("->");
            curr = curr.next;
        }
        stringBuilder.append(curr.data);
        return stringBuilder.toString();
    }
}

class Node {
    int data;
    Node next = null;

    public Node(int data) {
        this.data = data;
    }
}