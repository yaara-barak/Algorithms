package LIS;

import java.util.ArrayList;
import java.util.Arrays;

public class dynamic {

    public static void main(String[] args) {
        int arr[] = {0, 8, 4, 12, 2, 2, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};

        // LIS using LCS
        LIS_using_LCS(arr);

        LIS_length(arr);

    }
    //dynamic
    private static void LIS_using_LCS(int[] arr) {
        int sorted[] = new int[arr.length];
        // copy array
        for (int i = 0; i < arr.length; i++) {
            sorted[i] = arr[i];
        }
        //sort
        Arrays.sort(sorted);

        //calculate matrix and subsequence
        int[][] Mat = LCS(arr, sorted);
        String subsequence = GetSubsequence(arr, sorted, Mat);
        System.out.println("dynamic algorithm using LCS = " + Mat[arr.length][arr.length] + ", increasing subsequence = " + subsequence);

    }

    //dynamic
    public static int[][] LCS(int[] x, int[] y) {
        int[][] Mat = new int[x.length + 1][y.length + 1];
        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                if (x[i - 1] == y[j - 1]) {
                    Mat[i][j] = Mat[i - 1][j - 1] + 1;
                } else
                    Mat[i][j] = Math.max(Mat[i][j - 1], Mat[i - 1][j]);
            }
        }
        return Mat;
    }

    //dynamic
    public static String GetSubsequence(int[] x, int[] y, int[][] mat) {
        int size = x.length;
        int i = size;
        int j = size;
        String ans = "";
        while (i > 0 && j > 0) {
            if (x[i - 1] == y[j - 1]) {
                ans = x[i - 1] + ", " + ans;
                i = i - 1;
                j = j - 1;
            } else {
                if (mat[i - 1][j] > mat[i][j - 1])
                    i--;
                else
                    j--;
            }
        }
        return ans;
    }

    //not dynamic- o(n log n)
    private static void LIS_length(int[] arr) {
        int []help= new int[arr.length];
        int size=0;
        help[0]=arr[0];
        size++;
        for (int i=1; i< arr.length;i++){
            int index= FindRightIndexWithBinarySearch(help,arr[i],0,size-1 );
            help[index]=arr[i];
            if (index==size)
                size++;
        }
        System.out.println("the length of the LIS is :"+size);
    }

    //not dynamic- o(n(n+log n))
    private static int[] LIS_example(int[] arr) {
        int [][]mat= new int[arr.length][arr.length];
        mat[0][0]=arr[0];
        int size=1;
        for (int i=1; i< arr.length;i++){
            int index= binarySearchBetween(mat,size,arr[i]);
            mat[index][index]=arr[i];
            if (index==size)
                size++;
           copy(mat,index);
        }
        int[] ans = new int[size];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = mat[size-1][i];
        }
        return ans;
    }


    private static int FindRightIndexWithBinarySearch(int[]temp, int num, int left, int right) {
        if (num < temp[left])
            return left;
        if (num > temp[right])
            return right+1;
        if (left == right)
            return left;
        int mid = (left+right)/2;

        if (temp[mid]>num)
            return FindRightIndexWithBinarySearch(temp, num, left, mid);
        else
            return FindRightIndexWithBinarySearch(temp, num, mid+1, right);
    }


    private static int binarySearchBetween(int[][] mat, int end, int v) {
        if(v > mat[end-1][end-1]) return end;
        if(v < mat[0][0]) return 0;
        int high = end, low = 0;
        while(low <= high) {
            if(low == high)return low;
            int mid = (low + high)/2;
            if(mat[mid][mid] == v) return mid;
            if(mat[mid][mid] > v) high = mid;
            else low = mid+1;
        }
        return -1;
    }


    private static void Print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }


    private static void copy(int[][] mat, int index) {
        for (int i = 0; i < index; i++) {
            mat[index][i] = mat[index-1][i];
        }
    }

}
