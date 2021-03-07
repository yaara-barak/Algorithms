package LIS;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.sort;

public class test {

    public static void main(String[] args) {
        int arr[] = {1,11,2,10,4,5,2,1};
        //LIS_length(arr);
        int arr1[] = {9,10,8,0,1,4,3,7};
        //absolute(arr1, 2);
        int arr2[][] = {{0, 5, 8, 3, 11, 7, 9, 61}, {1, 1, 1, 1, 0, 0, 1, 1}};
        //Ones(arr2);
        System.out.println(lbs1(arr) + "------");
        //System.out.println(circle(arr1));
        //System.out.println(Arrays.toString(absolute(arr, 3)));
    }

    //מחזירה את האורך של ה-LIS הארוך ביותר בo(n log n)
    public static int LIS_length(int[] arr) {
        int[] help = new int[arr.length];
        int size = 0;
        help[0] = arr[0];
        size++;
        for (int i = 1; i < arr.length; i++) {
            int index = FindRightIndexWithBinarySearch(help, arr[i], 0, size - 1);
            help[index] = arr[i];
            if (index == size)
                size++;
        }
        return +size;
    }

    //פונקצית עזר חיפוש בינארי
    private static int FindRightIndexWithBinarySearch(int[] temp, int num, int left, int right) {
        if (num < temp[left])
            return left;
        if (num > temp[right])
            return right + 1;
        if (left == right)
            return left;
        int mid = (left + right) / 2;

        if (temp[mid] > num)
            return FindRightIndexWithBinarySearch(temp, num, left, mid);
        else
            return FindRightIndexWithBinarySearch(temp, num, mid + 1, right);
    }

    //פונקציה המחזירה תת מערך של ARR שההפרשים בערך מוחלט בין כל זוג איברים עוקבים הוא K
    private static int[] absolute(int[] arr, int k) {
        int[] temp = new int[arr.length];
        int max = 0;
        int maxI = -1;
        int maxL = 0;
        for (int i = 0; i < arr.length; i++) {
            maxL = 0;
            if (i == 0)
                if (Math.abs(arr[i] - arr[i + 1]) == k)
                    temp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (Math.abs(arr[i] - arr[j]) == k && temp[j] > maxL)
                    maxL = temp[j];
                temp[i] = maxL + 1;
            }
            if (temp[i] > max) {
                max = temp[i];
                maxI = i;
            }
        }
        int[] ans = new int[max];
        int i = max - 1;
        ans[i] = arr[maxI];
        i--;
        for (int t = maxI - 1; t >= 0; t--) {
            if (temp[t] == max - 1)
                if (arr[t] == arr[maxI] + k || arr[t] == arr[maxI] - k) {
                    ans[i] = arr[t];
                    i--;
                    max--;
                    maxI = t;
                }
        }
        return ans;
    }

    //פונקציה שמקבלת מטריצה בעלת שתי שורות- שורת מספרים ושורה בינארית וצריכה להחזיר LIS רק של האיברים ששוים 1
    private static void Ones(int[][] arr) {
        int count = 0;
        int mone = 0;
        for (int j = 0; j < arr[0].length; j++)
            if (arr[1][j] == 1) count++;
        int[] help = new int[count];
        for (int j = 0; j < arr[0].length; j++)
            if (arr[1][j] == 1) {
                help[mone] = arr[0][j];
                mone++;
            }
        LIS_length(help);
    }

    //פונקציה שמקבלת מעגל של מספרים וצריכה להחזיר את המחרוזת העולה הארוכה ביותר במעגל
    private static String circle(int[] arr1) {
        int[] arr = Arrays.copyOf(arr1, arr1.length);
        Arrays.sort(arr1);
        int[] tempArr = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        int max = LIS_length(arr);
        for (int i = 0; i < n; i++) {
            int[] b = new int[n];
            int k = (i + 1) % n;
            for (int j = 0; j < n; j++) {
                b[j] = arr[k];
                k = (k + 1) % n;
            }
            int f = LIS_length(b);
            if (f > max) {
                max = f;
                tempArr = Arrays.copyOf(b, b.length);

            }
        }
        int[] tempArr2 = Arrays.copyOf(tempArr, tempArr.length);
        Arrays.sort(tempArr);
        int[][] mat = LCS_mat(tempArr, tempArr2);
        if (tempArr.equals(Arrays.copyOf(arr, arr.length)))
            return GetSubsequence(arr, arr1, LCS_mat(arr, arr1));;
        return  GetSubsequence(tempArr, tempArr2, mat);
    }

    //פונקציה המחזירה את המחרוזת של LIS
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

    //פונקציה המחזירה את הגודל של התת מערך העולה ואז יורד הארוך ביותר
    public static int UpDown(int[] x) {
        int maxInd = -1;
        int[] up;
        int[] down;
        int sum;
        int max = LIS_length(x);
        for (int i = 1; i < x.length; i++) {
            up = new int[i + 1];
            down = new int[x.length - i - 1];
            for (int j = 0; j < up.length; j++)
                up[j] = x[j];
            for (int t = 0; t < down.length; t++)
                down[t] = x[x.length - t - 1];
            if (down.length == 0)
                sum = LIS_length(up);
            else
                sum = LIS_length(up) + LIS_length(down);
            if (sum > max) {
                max = sum;
                maxInd = i;
            }
        }
        System.out.println(maxInd);
        return max;
    }

    //פונקציה המחזירה את האורך של התת מחרוזת היורדת הארוכה ביותר
    public static int LDS(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr);
        int[] b = new int[arr.length];
        int j = arr.length;
        for (int i = 0; i < arr.length; i++) {
            b[j - 1] = arr[i];
            j = j - 1;
        }
        return LCS_value(a, b);
    }

    //פונקציה המחזירה את ה-LIS  בעזרת LCS
    public static int LCS_value(int[] x, int[] y) {
        int[][] Mat = new int[x.length + 1][y.length + 1];
        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                if (x[i - 1] == y[j - 1]) {
                    Mat[i][j] = Mat[i - 1][j - 1] + 1;
                } else
                    Mat[i][j] = Math.max(Mat[i][j - 1], Mat[i - 1][j]);
            }
        }
        return Mat[x.length - 1][y.length - 1];
    }

    //פונקציה המחזירה את המטריצה של LCS
    public static int[][] LCS_mat(int[] x, int[] y) {
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

    //פונקציה המחזירה תת מערך של ARR שהאיבר הוקדם לו או השורש שלו או הריבוע שלו
    public static int[] Square(int[] arr) {
        int[] temp = new int[arr.length];
        int max = 0;
        int maxI = -1;
        int maxL = 0;
        double s;
        int p;
        for (int i = 0; i < arr.length; i++) {
            maxL = 0;
            p = arr[i] * arr[i];
            s = Math.sqrt(arr[i]);
            for (int j = i - 1; j >= 0; j--) {

                if (arr[j] == s && temp[j] > maxL) {
                    maxL = temp[j];
                } else if (arr[j] == p && temp[j] > maxL) {
                    maxL = temp[j];
                }
                temp[i] = maxL + 1;
            }
            if (temp[i] > max) {
                max = temp[i];
                maxI = i;
            }
        }
        int[] ans = new int[max];
        int i = max - 1;
        ans[i] = arr[maxI];
        i--;
        for (int t = maxI - 1; t >= 0; t--) {
            if (temp[t] == max - 1)
                if (arr[t] == Math.sqrt(arr[maxI]) || arr[t] == arr[maxI] * arr[maxI]) {
                    ans[i] = arr[t];
                    i--;
                    max--;
                    maxI = t;
                }
        }
        return ans;
    }

    //פונקציה המחזירה את התת סדרה הקצרה ביותר עם הסכום הגבוהה ביותר
    public static int maxSum(int[] arr) {
        int[] value = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if(arr[i]>arr[j] && value[j]>max) max=value[j];
            }
            value[i]=max+arr[i];
        }
        int maxValue = 0;
        for(int i=0; i<value.length; i++){
            if(value[i]>maxValue) maxValue=value[i];
        }

        return maxValue;
    }

    //
    public static int  lbs1(int[] arr){
        int[] up;
        int[] down;
        int max = LDS(arr);
        int sum;
        for(int i=0; i<arr.length; i++){
            up = new int[i + 1];
            down = new int[arr.length - i - 1];
            for (int j = 0; j < up.length; j++)
                up[j] = arr[j];
            for (int t = 0; t < down.length; t++)
                down[t] = arr[arr.length - t - 1];
            if (down.length == 0)
                sum = LIS_length(up);
            else
                sum = LIS_length(up) + LIS_length(down);
            if (sum > max)
                max = sum;
        }
        return max;
    }

    public static int  lbs2(int[] arr){
        int n = arr.length;
        int[] arr_lds = new int[n];
        for (int i = 0; i < n; i++) {
            arr_lds[i]= arr[n-i-1];
        }
        int[] lisArr = new int[n];
        lisArr[0] = arr[0];
        int[] ldsArr = new int[n];
        ldsArr[0] = arr_lds[0];
        int[] upLen = new int[n+1];
        int[] downLen = new int[n+1];
        int len = 1;
        upLen[1] = 1;
        downLen[n-1] = 1;
        for (int i = 1; i < n; i++) {
            int index = binarySearchBetween(lisArr,len,arr[i]);
            lisArr[index] = arr[i];
            if(index == len) len++;
            upLen[i+1] = len;
        }
        len = 1;
        for (int i = 1; i < n; i++) {
            int index = binarySearchBetween(ldsArr,len,arr_lds[i]);
            ldsArr[index] = arr_lds[i];
            if(index == len) len++;
            downLen[n-i-1] = len;
        }
        //System.out.println(Arrays.toString(upLen));
        //System.out.println(Arrays.toString(downLen));
        int max = upLen[1] + downLen[0] - 1;
        for (int i = 1; i < n; i++) {
            int lbs = upLen[i+1] + downLen[i] -1;
            if(lbs > max) {
                max = lbs;
            }
        }
        return max;
    }

    private static int binarySearchBetween(int[] arr, int end, int v) {
        if(v > arr[end-1]) return end;
        if(v < arr[0]) return 0;
        int high = end, low = 0;
        while(low <= high) {
            if(low == high)return low;
            int mid = (low + high)/2;
            if(arr[mid] == v) return mid;
            if(arr[mid] > v) high = mid;
            else low = mid+1;
        }
        return -1;
    }
}