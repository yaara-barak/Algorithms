package OnesMatrix;

import java.util.Stack;

public class test {

    public static void main(String[] args) {
//        int[][] mat = {{1, 0, 1, 1},
//                {1, 1, 0, 0},
//                {1, 0, 0, 1},
//                {1, 0, 0, 0}};
        int[][] mat = new int[4][5];
        mat[0] = new int[]{1, 1, 1, 0, 1};
        mat[1] = new int[]{1, 1, 1, 1, 0};
        mat[2] = new int[]{0, 1, 1, 1, 0};
        mat[3] = new int[]{0, 1, 1, 0, 0};
//        int max= biggestOnesMatrix(mat);
//        System.out.println(max + "*" + max + "=" + max * max);
//        numberOfMatrix(mat, 3);
//        numberOfBiggestMatrix(mat);
//        MaxOnesZeroMatrix(mat);
        int A[][] = {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
        };
        numberOfMatrix(A,1);
    }

    public static int[][] helpMatrix(int[][] mat) {
        int[][] help = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            help[i][0] = mat[i][0];
        }
        for (int j = 0; j < mat[0].length; j++) {
            help[0][j] = mat[0][j];
        }
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    help[i][j] = 1 + Math.min(Math.min(help[i - 1][j], help[i][j - 1]), help[i - 1][j - 1]);
                } else
                    help[i][j] = 0;
            }
        }

        return help;
    }


    public static void MaxSumSub(int[][] mat, int k) {
        int[][] help = new int[mat.length][mat.length];
        for (int j = 0; j < mat.length; j++) {
            int sum = 0;
            for (int i = 0; i < k; i++)
                sum = sum + mat[i][j];
            help[0][j] = sum;
            for (int i = 1; i < mat.length - k + 1; i++) {
                sum = sum + (mat[i + k - 1][j] - mat[i - 1][j]);
                mat[i][j] = sum;
            }
        }

    }

    //מחזירה את הגודל של הריבוע אחדות הגדול ביותר במערך
    public static int biggestOnesMatrix(int[][] mat) {
        int max = 0;
        int[][] help = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) help[i][0] = mat[i][0];
        for (int j = 0; j < mat[0].length; j++) help[0][j] = mat[0][j];
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                if (mat[i][j] == 0) help[i][j] = 0;
                else {
                    help[i][j] = 1 + Math.min((Math.min(help[i - 1][j], help[i][j - 1])), help[i - 1][j - 1]);
                    if (help[i][j] > max)
                        max = help[i][j];
                }
            }
        }
        return max;
    }

    // מחזירה את כמות הריבועים בגודל K במטריצה
    public static void numberOfMatrix(int[][] mat, int k) {
        int count = 0;
        int[][] help = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            help[i][0] = mat[i][0];
            if (help[i][0] >= k)
                count++;
        }
        for (int j = 0; j < mat[0].length; j++) {
            help[0][j] = mat[0][j];
            if (help[0][j] >= k)
                count++;

        }
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                if (mat[i][j] == 0) help[i][j] = 0;
                else {
                    help[i][j] = 1 + Math.min((Math.min(help[i - 1][j], help[i][j - 1])), help[i - 1][j - 1]);
                    if (help[i][j] >= k)
                        count++;
                }
            }
        }
        System.out.println(count);
        Print(help);

    }

    public static void Print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //מספר המטריצות הגדולות ביותר
    public static void numberOfBiggestMatrix(int[][] mat) {
        int count = 0;
        int max = biggestOnesMatrix(mat);
        int[][] help = helpMatrix(mat);
        Print(help);
        for (int i = 0; i < help.length; i++)
            for (int j = 0; j < help[0].length; j++)
                if (help[i][j] == max)
                    count++;

        System.out.println("the numbers of the biggestMat are " + count);

    }

    //האורך של הפלוס אחדות הגדול ביותר במערך
    public static int largestPlus(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] u = new int[n][m];
        int[][] d = new int[n][m];
        int[][] l = new int[n][m];
        int[][] r = new int[n][m];
        for (int i = 0; i < m; i++) {
            u[0][i] = mat[0][i];
        }
        for (int i = 0; i < m; i++) {
            d[n - 1][i] = mat[n - 1][i];
        }
        for (int i = 0; i < n; i++) {
            l[i][0] = mat[i][0];
        }
        for (int i = 0; i < n; i++) {
            r[i][m - 1] = mat[i][m - 1];
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] != 0) {
                    if (i != 0) {
                        u[i][j] = u[i - 1][j] + 1;
                    }
                    if (j != 0) {
                        l[i][j] = l[i][j - 1] + 1;
                    }
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    if (i != n - 1) {
                        d[i][j] = d[i + 1][j] + 1;
                    }
                    if (j != m - 1) {
                        r[i][j] = r[i][j + 1] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (Math.min(Math.min(u[i][j], d[i][j]), Math.min(l[i][j], r[i][j])) > max) {
                    max = Math.min(Math.min(u[i][j], d[i][j]), Math.min(l[i][j], r[i][j]));
                }
            }
        }
        return (max - 1) * 4 + 1;
    }

    //מערך עזר למציאת מטריצת האפסים הגדולה ביותר
    public static int helpMatrixZero(int[][] mat) {
        int max = 0;
        int[][] help = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) help[i][0] = mat[i][0];
        for (int j = 0; j < mat[0].length; j++) help[0][j] = mat[0][j];
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                if (mat[i][j] == 1) help[i][j] = 0;
                else {
                    help[i][j] = 1 + Math.min((Math.min(help[i - 1][j], help[i][j - 1])), help[i - 1][j - 1]);
                    if (help[i][j] > max)
                        max = help[i][j];
                }
            }
        }
        return max;
    }

    //פונקציה שבודקת מה הגודל של הריבוע אחדות\אפסים המקסימלי
    public static void MaxOnesZeroMatrix(int[][] mat) {
        int Max0 = helpMatrixZero(mat);
        int Max1 = biggestOnesMatrix(mat);
        if (Max0 > Max1)
            System.out.println("the biggest subMatrix size is " + Max0 + " combine from zeros");
        else
            System.out.println("the biggest subMatrix size is " + Max1 + " combine from ones");
    }

    //הגודל של המטריצה המלבנית הגדולה ביותר
    public static int maxRectangularSubMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] sumOfCols = new int[row][col];
        for (int i = 0; i < col; i++) {
            sumOfCols[0][i] = mat[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0)
                    sumOfCols[i][j] = 0;
                else
                    sumOfCols[i][j] = sumOfCols[i - 1][j] + 1;
            }
        }
        int max = 0;
        for (int i = 0; i < row; i++) {
            int area = maxAreaSubMatrix(sumOfCols[i]);
            if (area > max) {
                max = area;
            }
        }
        return max;
    }

    //פונקצית עזר למציאת המלבן
    public static int maxAreaSubMatrix(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<Integer>();
        int top, area, max = 0;
        int i = 0;
        while (i < n) {
            if (st.isEmpty() || arr[st.peek()] <= arr[i]) {
                st.push(i++);
            } else {
                top = st.pop();
                if (st.isEmpty())
                    area = arr[top] * i;
                else
                    area = arr[top] * (i - st.peek() - 1);
                if (area > max) {
                    max = area;
                }
            }
        }
        while (!st.isEmpty()) {
            top = st.pop();
            if (st.isEmpty())
                area = arr[top] * i;
            else
                area = arr[top] * (i - st.peek() - 1);
            if (area > max) {
                max = area;
            }
        }
        return max;
    }

    //מספר המלבנים השחורים במערך
    public static int numBlackRectangles(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    if ((i == 0 || mat[i - 1][j] == 0) && (j == 0 || mat[i][j - 1] == 0)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}