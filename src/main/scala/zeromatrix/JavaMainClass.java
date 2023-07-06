package zeromatrix;

import java.util.Arrays;
import java.util.HashSet;

public class JavaMainClass {

    public static void main(String[] args) {
        int[][] input = {
                {1, 2, 3},
                {5, 5, 0},
                {6, 7, 8},
                {9, 9, 9}
        };
        zeroMatrix(input);
        System.out.println(Arrays.deepToString(input));
    }

    private static void zeroMatrix(int[][] matrix) {
        HashSet<Integer> zeroRows = new HashSet<>();
        HashSet<Integer> zeroColumns = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroColumns.add(j);
                }
            }
        }

        for (int i : zeroRows) {
            for (int j : zeroColumns) {
                zerofy(matrix, i, j);
            }
        }
    }

    private static void zerofy(int[][] matrix, int row, int column) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][column] = 0;
        }
        Arrays.fill(matrix[row], 0);
    }
}
