package kmax;

import java.util.Arrays;

public class JavaMainClass {

    public static void main(String[] args) {
        int[] array = {2, 5, -1, 6, 10, 3, 2, 1};
        System.out.println(kthMax(array, 3));
        System.out.println(Arrays.toString(array));
    }

    public static int kthMax(int[] array, int k) {
        if (k > array.length) throw new RuntimeException("k > array.length");

        for (int i = 0; i < k; i++) {
            int maxIndex = i;
            for(int j = i; j < array.length; j++) {
                if(array[maxIndex] < array[j]) {
                    maxIndex = j;
                }
            }
            int temp = array[maxIndex];
            array[maxIndex] = array[i];
            array[i] = temp;
        }

        return array[k - 1];
    }
}