package LCS;

import java.util.Arrays;
import java.util.LinkedList;

public class tests {


    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4, 1, 6};
        int[] y = {2, 3, 1, 4, 2};
        int[] z = {2};
//        System.out.println(LCS_example(x, y));
//        System.out.println(LCS_example(x, y, z));
//        System.out.println(LCSContinuous(x, y));
//        LinkedList<String> str = ALL_LCS_example(y, x);
//        for (int i = 0; i < str.size(); i = i + 2) {
//            System.out.println(str.get(i));
//        }
//        System.out.println(SCS(x, y));
//        String a = "abcdefh";
//        String b = "bcefg";
//        LCSminOP(a, b);
//        System.out.println(LongestContinuousString("abcbbabcde"));
    }

    //מחזירה את המטריצה של LCS
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

    //מחזירה את האורך של LCS
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

    //מחזירה דוגמה לאחת התת מחרוזות הארוכות ביותר
    public static String LCS_example(int[] x, int[] y) {
        String lcs = "";
        int[][] mat = new int[x.length + 1][y.length + 1];
        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                if (x[i - 1] == y[j - 1])
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                else
                    mat[i][j] = Math.max(mat[i][j - 1], mat[i - 1][j]);
            }
        }
        int i = x.length;
        int j = y.length;
        while (i > 0 && j > 0) {
            if (x[i - 1] == y[j - 1]) {
                lcs = x[i - 1] + " " + lcs;
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

    //מחזירה דוגמה לאחת מתתי המחרוזות האורוכת ביותר של שני STRING
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

    //מחזירה דוגמה של LCS של שלושה מערכים
    public static String LCS_example(int[] x, int[] y, int[] z) {
        String lcs = " ";
        int[][][] mat = new int[x.length + 1][y.length + 1][z.length + 1];
        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                for (int k = 1; k <= z.length; k++) {
                    if (x[i - 1] == y[j - 1] && y[j - 1] == z[k - 1])
                        mat[i][j][k] = mat[i - 1][j - 1][k - 1] + 1;
                    else
                        mat[i][j][k] = Math.max((Math.max(mat[i - 1][j][k], mat[i][j - 1][k])), mat[i][j][k - 1]);
                }
            }
        }
        int i = x.length;
        int j = y.length;
        int k = z.length;
        while (i > 0 && j > 0 && k > 0) {
            if (x[i - 1] == y[j - 1] && z[k - 1] == y[j - 1]) {
                lcs = x[i - 1] + " " + lcs;
                i--;
                j--;
                k--;
            } else {
                if (mat[i - 1][j][k] > mat[i][j - 1][k] && mat[i - 1][j][k] > mat[i][j][k - 1])
                    i--;
                else {
                    if (mat[i][j - 1][k] > mat[i - 1][j][k] && mat[i][j - 1][k] > mat[i][j][k - 1])
                        j--;
                    else
                        k--;
                }
            }
        }

        return "{ " + lcs + "}";
    }

    //מחזירה את התת מחרוזת הרציפה המשותפת הארוכה ביותר
    public static int LCSContinuous(int[] x, int[] y) {
        int max = 0;
        int[][] mat = new int[x.length + 1][y.length + 1];
        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                if (x[i - 1] == y[j - 1])
                    mat[i][j] = 1 + mat[i - 1][j - 1];
                else
                    mat[i][j] = 0;
                if (mat[i][j] > max) max = mat[i][j];
            }
        }
        return max;
    }

    //מחזירה את כל התתי מחרוזות הכי ארוכות
    public static LinkedList<String> ALL_LCS_example(int[] x, int[] y) {
        int[][] mat = LCS_mat(x, y);
        int i = mat.length - 1;
        int j = mat[0].length - 1;
        return (lcs_example(mat, x, y, i, j));
    }

    //פונקצית עזר לריקורסיה של החזרת כל המחרוזות
    private static LinkedList<String> lcs_example(int[][] mat, int[] x, int[] y, int i, int j) {
        LinkedList<String> lcs = new LinkedList<>();
        if (i == 0 || j == 0) lcs.add("");
        else if (x[i - 1] == y[j - 1])
            for (String st : lcs_example(mat, x, y, i - 1, j - 1))
                lcs.add(st + x[i - 1]);
        else {
            if (mat[i - 1][j] >= mat[i][j - 1])
                lcs.addAll(lcs_example(mat, x, y, i - 1, j));
            if (mat[i][j - 1] >= mat[i - 1][j])
                lcs.addAll(lcs_example(mat, x, y, i, j - 1));
        }
        return lcs;
    }

    public static void Print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //מחזירה את המחרוזת המשותפת הקצרה ביותר של X,Y
    public static String SCS(int[] x, int[] y) {
        int l = LCS_length(x, y);
        l = x.length + y.length - l;
        String lcs = LCS_example(x, y);
        String[] LCS = lcs.split(" ");
        int ent[] = new int[LCS.length];
        for (int i = 0; i < LCS.length; i++) {
            try {
                ent[i] = Integer.parseInt(LCS[i]);
            } catch (NumberFormatException e) {
            }
        }
        String ans = "";
        int i = 0;
        int j = 0;
        int counter = 0;
        while (counter <= LCS.length && j < y.length && i < x.length) {
            while (x[i] != ent[counter] || y[j] != ent[counter]) {
                ans = ans + x[i] + y[j];
                i++;
                j++;
            }
            if (x[i] == ent[counter] && y[j] != ent[counter])
                while (y[j] != ent[counter]) {
                    ans = ans + y[j];
                    j++;
                }
            else if (y[j] == ent[counter] && x[i] != ent[counter])
                while (x[i] != ent[counter]) {
                    ans = ans + x[i];
                    i++;
                }
            if (x[i] == ent[counter] && y[j] == ent[counter]) {
                ans = ans + x[i];
                i++;
                j++;
                counter++;
            }
        }
        if (j == y.length)
            while (i < x.length) {
                ans = ans + x[i];
                i++;
            }
        else if (i == x.length) {
            ans = ans + y[j];
            j++;
        }
        return ans;

    }

    //מדפיסה את כמות הפעולות הדרושות כדי להפוך את מחרוזת X למחרוזת Y
    public static void LCS_min_OP(String x, String y) {
        String lcs = LCS_String(x, y);
        int add = 0;
        int del = 0;
        if (x.length() > y.length()) {
            add = y.length() - lcs.length();
            del = x.length() - lcs.length();
        } else {
            del = y.length() - lcs.length();
            add = x.length() - lcs.length();
        }
        System.out.println("Number of deleting- " + del);
        System.out.println("Number of adding- " + add);
    }

    //פונקציה המחזירה את התת מחרוזת הארוכה ביותר של X שאין בה חזרה על אותיות
    public static String LongestContinuousString(String x) {
        int max = 0;
        int maxI = 0;
        int[] arr = new int[x.length()];
        arr[0] = 1;
        for (int i = 0; i < x.length() - 1; i++) {
            if (x.charAt(i) != x.charAt(i + 1))
                arr[i + 1] = arr[i] + 1;
            else
                arr[i + 1] = 1;
            if (arr[i + 1] > max) {
                max = arr[i + 1];
                maxI = i + 1;
            }
        }
        String ans = "";
        for (int i = maxI; i > maxI - max; i--)
            ans = x.charAt(i) + ans;
        return ans;
    }
}