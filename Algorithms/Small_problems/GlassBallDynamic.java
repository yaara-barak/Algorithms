package Small_problems;

import java.util.Arrays;

public class GlassBallDynamic {

    public static void main(String[] args) {
        System.out.println(BestFlorToStart(3, 64));
    }

    //n flores k balls
    public static int DynamicKBallsProb(int n, int k) {
        int[][] arr = new int[k + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            arr[0][j] = 0;
            arr[1][j] = j;
        }
        for (int i = 2; i <= k; i++) {
            arr[i][0] = 0;
            arr[i][1] = 1;
            if (n >= 2) {
                arr[i][2] = 2;
            }
            for (int j = 2; j <= n; j++) {
                int min = n + 1;
                for (int p = 1; p < j; p++) {
                    min = Math.min(Math.max(arr[i - 1][p - 1], arr[i][j - p]), min);
                }
                arr[i][j] = min + 1;

            }
            System.out.println(Arrays.toString(arr[i]));
        }
        return arr[k][n];
    }

    private static void Print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static int flor(int n) {
        int sum = 0;
        int counter = 1;
        while (sum < n) {
            counter++;
            sum += counter;
        }
        return counter;
    }

    public static int BestFlorToStart(int balls, int flores) {
        int mat[][] = new int[balls + 1][flores + 1];
        int ans;
        int bestStartFlore = 0;
        int bestEndFlore = 0;

        for (int j = 1; j <= flores; j++) {
            mat[0][j] = 0;
            mat[1][j] = j;
        }
        for (int i = 1; i <= balls; i++) {
            mat[i][1] = 1;
            mat[i][0] = 0;
        }

        for (int i = 2; i <= balls; i++) {
            for (int j = 2; j <= flores; j++) {
                mat[i][j] = flores+1;
                for (int k = 1; k <= j; k++) {
                    ans = Math.max(mat[i][j - k],mat[i - 1][k - 1]) + 1;
                    if (ans < mat[i][j]) {
                        mat[i][j] = ans;
                        if (i == balls && j == flores) {
                            bestStartFlore = k;
                        }
                    }
                    if (ans == mat[i][j]) {
                        mat[i][j] = ans;
                        if (i == balls && j == flores) {
                            bestEndFlore = k;
                        }
                    }
                }
            }
        }
        for (int t = 0; t < mat.length; t++) {
            System.out.println(Arrays.toString(mat[t]));
        }
        System.out.println(bestStartFlore);
        System.out.println(bestEndFlore);
        return mat[balls][flores];
    }

}

