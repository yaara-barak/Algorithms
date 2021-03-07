package numbers_game;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] arr = {2, 8, 7, 4};
        buildMatrix(arr);
        path(arr);
    }

    public static int buildMatrix(int[] arr) {
        int[][] mat = new int[arr.length][arr.length];
        for (int t = 0; t < arr.length; t++)
            mat[t][t] = arr[t];
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                mat[i][j] = Math.max(arr[i] - mat[i + 1][j], arr[j] - mat[i][j - 1]);
            }
        }
        return mat[0][arr.length - 1];
    }

   //פונקציה המחזירה את המשחק ששחקן 1 הכי כדאי לו לשחק ושחקן 2 הכי כדאי לו לשחק
    public static void path(int[] arr) {
        int[][] mat = new int[arr.length][arr.length];
        for (int t = 0; t < arr.length; t++)
            mat[t][t] = arr[t];
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                mat[i][j] = Math.max(arr[i] - mat[i + 1][j], arr[j] - mat[i][j - 1]);
            }
        }
        int i = 0, j = arr.length - 1;
        int t = 0;
        int[] res = new int[arr.length];
        while (j - 1 > 0 && i < arr.length - 1) {
            if (i == j) {
                res[t] = i;
                t++;
                i = i + 1;
                j = j - 1;
            }
            if (mat[i][i] - mat[i + 1][j] > mat[j][j] - mat[i][j - 1]) {
                res[t] = i;
                i = i + 1;
                t++;
            } else {
                res[t] = j;
                j = j - 1;
                t++;
            }
        }
        if (i < arr.length - 1)
            res[t] = j;

        else res[t] = i;

        System.out.println("the best moves for player 1: ");
        for (int k = 0; k < res.length; k = k + 2)
            System.out.print(res[k] + "->");
        System.out.println("");
        System.out.println("the best moves for player 2: ");
        for (int g = 1; g < res.length; g = g + 2)
            System.out.print(res[g] + "->");
    }

    //פונקציה המקבלת מעגל של מספרים ומחזירה את הרווח המקסימלי של שחקן א
    public static int numberGameCycle(int[] arr) { // O(n^3)
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int a = arr[i];
            int[] b = new int[n - 1];
            int k = (i + 1) % n;
            for (int j = 0; j < n - 1; j++) {
                b[j] = arr[k];
                k = (k + 1) % n;
            }
            int f = buildMatrix(b);
            if (a - f > max)
                max = a - f;
        }
        return max;
    }

    // משחק המספרים עם שני מערכים
    public static int numberGameTwoArray(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        int[][][][] mat = new int[n][n][m][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                mat[i][i][j][j] = Math.max(arr1[i] - arr2[j], arr1[i] - arr2[i]);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = j + 1; k < m; k++) {
                    int[] b = Arrays.copyOfRange(arr2, j, k + 1);
                    mat[i][i][j][k] = Math.max(arr1[i] - buildMatrix(b), arr2[j] - mat[i][i][j + 1][k]);
                }
        return mat[0][0][m-1][m-1];
    }
}


