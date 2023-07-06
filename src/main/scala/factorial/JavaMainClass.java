package factorial;

import java.util.Stack;

public class JavaMainClass {

    public static void main(String[] args) {
        System.out.println("recursive result: " + factorialRec(10));
        System.out.println("iterative result: " + factorialIter(10));
    }

    public static int factorialRec(int value) {
        if(value <= 1) return 1;

        return value * factorialRec(value - 1);
    }

    public static int factorialIter(int value) {
        Stack<Integer> stack = new Stack<>();
        stack.add(value);

        int result = 1;
        while(!stack.isEmpty()) {
            Integer nextElem = stack.pop();
            if(nextElem <= 1) {
                break;
            }

            result *= nextElem;
            stack.add(nextElem - 1);
        }
        return result;

    }
}