package numbers_game;

public class dinamic {
    public static void main(String[] args) {
        int n;
        int[] arr = {1, 3, 6, 1, 3, 6};
        int[][] mat = new int[arr.length][arr.length];
        buildMatrix(mat, arr);
        Print(mat);
        System.out.println("the first player profit is "+mat[0][mat[0].length-1]);
        System.out.println(getOptimalPath(0,mat.length-1,mat));
    }

    public static void buildMatrix(int[][] mat, int[] arr) {
        for (int t = 0; t < arr.length; t++)
            mat[t][t] = arr[t];
        for (int i = mat.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < mat[0].length; j++) {
                mat[i][j] = Math.max(arr[i] - mat[i + 1][j], arr[j] - mat[i][j - 1]);
            }
        }
    }

    private static void Print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static String getOptimalPath(int i, int j, int[][] mat) {
        if(i == j)
            return "("+i+")"+ mat[i][i];
        if(mat[i][i] - mat[i+1][j] > mat[j][j] - mat[i][j-1]) {
            return "("+i+")"+mat[i][i] + "->" + getOptimalPath(i+1,j,mat);
        }
        else {
            return "("+j+")"+mat[j][j] + "->" + getOptimalPath(i,j-1,mat);
        }
    }

}







