package sortstack;

public class JavaMainClass {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(5);
        stack.push(7);
        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(-10);
        stack.push(10);
        System.out.println(stack);
        sortStack(stack);
        System.out.println(stack);
    }

    static void sortStack(Stack stack) {
        Stack tempStack = new Stack();
        FirstAttemptResults firstAttemptResults = doFirstAttempt(stack, tempStack);
        stack.push(firstAttemptResults.max);
        cleanup(tempStack, stack, firstAttemptResults.length, firstAttemptResults.max);
        moveEverything(stack, tempStack, firstAttemptResults.length - 1);

//        System.out.println(stack);

        for (int i = firstAttemptResults.length - 1; i > 0; i--) {
            int nextMax = findNextMax(tempStack, stack, i);
            cleanup(stack, tempStack, i, nextMax);
            stack.push(nextMax);
//            System.out.println(stack);
        }
    }

    static FirstAttemptResults doFirstAttempt(Stack originalStack, Stack temporaryStack) {
        int max = Integer.MIN_VALUE;
        int length = 0;
        while (!originalStack.isEmpty()) {
            int nextElem = originalStack.pop();
            if (nextElem > max) {
                max = nextElem;
            }
            temporaryStack.push(nextElem);
            length++;
        }

        return new FirstAttemptResults(max, length);
    }

    static int findNextMax(Stack from, Stack to, int size) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            int nextElem = from.pop();
            if (nextElem > max) {
                max = nextElem;
            }
            to.push(nextElem);
        }
        return max;
    }

    static void cleanup(Stack from, Stack to, int size, int nextMax) {
        boolean removed = false;
        for (int i = 0; i < size; i++) {
            int nextElem = from.pop();
            if (nextElem == nextMax && !removed) {
                removed = true;
            } else {
                to.push(nextElem);
            }
        }
    }

    static void moveEverything(Stack from, Stack to, int size) {
        for (int i = 0; i < size; i++) {
            int nextElem = from.pop();
            to.push(nextElem);
        }
    }
}

class Stack {
    Node head = null;

    public boolean isEmpty() {
        return head == null;
    }

    public void push(int data) {
        Node newHead = new Node(data);
        newHead.next = this.head;
        this.head = newHead;
    }

    public int peek() {
        return head.data;
    }

    public int pop() {
        int result = peek();
        head = head.next;
        return result;
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

class FirstAttemptResults {
    int max;
    int length;

    public FirstAttemptResults(int max, int length) {
        this.max = max;
        this.length = length;
    }
}