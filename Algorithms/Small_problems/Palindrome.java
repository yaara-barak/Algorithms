package Small_problems;

public class Palindrome {
    public static void main(String[] args) {
        longestPalSubStr("alfalfa");
    }


    public static void longestPalSubStrInARow(String str) {
        int n = str.length();
        boolean mat[][] = new boolean[n][n];
        int maxLength = 1;
        for (int i = 0; i < n; ++i)
            mat[i][i] = true;

        int start = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                mat[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }
        for (int k = 3; k <= n; ++k) {
            for (int i = 0; i < n - k + 1; ++i) {
                int j = i + k - 1;
                if (mat[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        Print(mat);
        System.out.println("Longest palindrome substring is: "+str.substring(start,start + maxLength ));
        System.out.println("Length is: " +maxLength);
    }

    static void Print(boolean[][] mat){
        for(int i=0;i<mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++)
                System.out.print(mat[i][j]+", ");
            System.out.println();
        }
    }

    public static int[][] LCS_mat(int[] x, int[] y) {
        int[][] mat = new int[x.length + 1][y.length + 1];
        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                if (x[i - 1] == y[j - 1])
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                else
                    mat[i][j] = Math.max(mat[i][j - 1], mat[i - 1][j]);
            }
        }

        return mat;
    }

    public static int LCS_length(int[] x, int[] y) {
        int[][] mat = new int[x.length + 1][y.length + 1];
        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                if (x[i - 1] == y[j - 1])
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                else
                    mat[i][j] = Math.max(mat[i][j - 1], mat[i - 1][j]);
            }
        }

        return mat[x.length - 1][y.length - 1];
    }

    public static String LCS_String(String x, String y) {
        String lcs = "";
        int[][] mat = new int[x.length() + 1][y.length() + 1];
        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1))
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                else
                    mat[i][j] = Math.max(mat[i][j - 1], mat[i - 1][j]);
            }
        }
        int i = x.length();
        int j = y.length();
        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                lcs = x.charAt(i - 1) + "" + lcs;
                i--;
                j--;
            } else {
                if (mat[i][j - 1] > mat[i - 1][j])
                    j--;
                else i--;
            }
        }
        return lcs;
    }

    public static void longestPalSubStr(String str){
        String str1= str;
        String str2="";
        for(int i=0;i<str.length();i++)
            str2=str.charAt(i)+str2;
        if(LCS_String(str1,str2).length()<LCS_String(str2,str1).length())
            System.out.println(LCS_String(str2,str1));
        else
            System.out.println(LCS_String(str1,str2));
    }

}