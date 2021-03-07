package Small_problems;

public class CoinsProblem {
    public static void main(String[] args) {
        int mat[][] = {
                   {5,0,0,0},
                  {5,4,0,0},
                 {2,1,8,0},
                {1,7,4,3}};
        System.out.println(maxGold(mat));
    }
    public static String maxGold(int[][] stairs) {
        int n = stairs.length;
        int[][] mat = new int[n][n];
        mat[0][0] = stairs[0][0];
        for (int i = 1; i < n; i++) {
            mat[i][0] = mat[i-1][0] + stairs[i][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                mat[i][j] = Math.max(mat[i-1][j],mat[i-1][j-1]) + stairs[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++)
                System.out.print(mat[i][j]+" ");
            System.out.println();
        }
        int max = mat[n-1][0];
        int index = 0;
        for (int i = 1; i < n; i++) {
            if(mat[n-1][i] > max) {
                max = mat[n-1][i];
                index = i;
            }
        }
        String path = "[" + index + ": " + stairs[n-1][index] + "]";
        for (int i = n-2; i >= 0; i--) {
            if(mat[i][index] < mat[i][index-1]){
                index--;
            }
            path = "[" + index + ": " + stairs[i][index] + "]" + "->" + path;
        }
        return path + " , price: " + max;
    }

}
