package triplestep;

public class JavaMainClass {

    public static void main(String[] args) {
        System.out.println(tripleStepCount(20));
    }

    public static int tripleStepCount(int n) {
        int[] memo = new int[n];
        return tripleStepCountRec(n, memo);
    }

    private static int tripleStepCountRec(int n, int[] memo) {
        return getFromMemoOrCalculate(n - 1, memo) +
                getFromMemoOrCalculate(n - 2, memo) +
                getFromMemoOrCalculate(n - 3, memo);
    }

    private static int getFromMemoOrCalculate(int n, int[] memo) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (memo[n - 1] != 0) return memo[n - 1];

        int calculated = tripleStepCountRec(n, memo);
        memo[n - 1] = calculated;
        return calculated;
    }
}