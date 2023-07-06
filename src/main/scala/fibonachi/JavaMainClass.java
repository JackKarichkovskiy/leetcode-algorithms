package fibonachi;

import java.util.Stack;

public class JavaMainClass {

    public static void main(String[] args) {
        System.out.println("recursive result: " + fibonachiRec(10));
//        System.out.println("iterative result: " + fibonachiIter(10));
    }

    public static int fibonachiRec(int value) {
        if (value == 0) return 0;
        if (value == 1) return 1;

        return fibonachiRec(value - 1) + fibonachiRec(value - 2);
    }

//    public static int fibonachiIter(int value) {
//        Stack<Integer> stack = new Stack<>();
//        stack.add(value);
//        stack.add(value + 1);
//
//        int result = 0;
//        while(!stack.isEmpty()) {
//            Integer nextElem = stack.pop();
//            if(nextElem <= 2) {
//                result += 3;
//                break;
//            }
//
//            result += nextElem;
//            stack.add(nextElem - 1);
//            stack.add(nextElem - 1);
//        }
//        return result;
//
//    }
}