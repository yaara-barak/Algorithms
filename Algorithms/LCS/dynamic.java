
package LCS;
import java.util.HashSet;
import java.util.Set;

public class dynamic {
    public static void main(String[] args) {
        String X = "ZBGCRDE";
        String Y = "YZGBCZD";
        //another example: x = abcabcaa, y = acbacba

        //calculate matrix
        int[][] Mat = LCS(X, Y);

        //print matrix after calculate
        Print(Mat);

        //print the LCS
        System.out.println("Longest Commom Subsequence = " + Mat[X.length()][Y.length()]);

        //print example of subsequence
        String subsequence = GetSubsequence(X, Y, Mat);
        System.out.println("Example of Common Subsequence = " + subsequence);
    }

    private static int[][] LCS(String x, String y) {
        int[][] Mat = new int[x.length()+1][y.length()+1];
        for (int i=1; i<=x.length();i++)
            for(int j=1;j<=y.length();j++)
                if (x.charAt(i-1)==y.charAt(j-1))
                    Mat[i][j]=Mat[i-1][j-1]+1;
                else
                    Mat[i][j] = Math.max(Mat[i][j-1], Mat[i-1][j]);

        return Mat;
    }

    private static void Print(int[][]  mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private static String GetSubsequence(String x, String y, int[][] mat) {
        int i = x.length();
        int j = y.length();
        String ans = "";
        while (i>0 && j>0){
            {
                if (x.charAt(i-1)==y.charAt(j-1))
                {
                    ans = x.charAt(i-1) + ans;
                    i = i-1;
                    j = j-1;
                }
                else
                {
                    if (mat[i-1][j] > mat[i][j-1])
                        i--;
                    else
                        j--;
                }
            }
        }
        return ans;
    }




}
