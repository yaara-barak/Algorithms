package OnesMatrix;

public class Dinamic {
    public static void main(String[] args) {
        int[][] mat = {{1, 1, 1},
                       {0, 1, 1},
                       {0, 0, 1}};
        biggestOnesMatrix(mat);
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

    public static void biggestOnesMatrix(int[][] mat) {
        int iMax = 0, jMax = 0;
        int max = 0;
        int[][] help = helpMatrix(mat);
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[0].length; j++) {
                System.out.print(help[i][j] + ",");
                if (help[i][j] > max) {
                    max = help[i][j];
                    iMax = i;
                    jMax = j;
                }
            }
            System.out.println();
        }
        System.out.println("The biggest ones matrix is " + max + "x" + max + " size, start at: (" + iMax + "," + jMax + ")");
    }

}