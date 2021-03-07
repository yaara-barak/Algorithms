package Small_problems;

import java.util.Arrays;

public class bigger_than_median {
    public static void main(String[] args) {
        System.out.println(getMistakeProb(10000, 2, 10000));
    }


    public static int getBiggerThanMedian(int[] arr, int check) {
        int max = arr[0];
        for (int i = 1; i < check; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }


    public static double getMistakeProb(int arr_size, int check, int count) {
        int incorrect = 0;
        int[] arr = new int[arr_size];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[j] = (int) (Math.random() * count * 10);
            }
            int x = getBiggerThanMedian(arr, check);
            Arrays.sort(arr);
            if (x < arr[arr.length / 2]) incorrect++;
        }
        return (double) incorrect / count;
    }


    public static int bestSolution(int []arr){//O(1)
        int max = arr[0];
        for (int i = 1; i < arr.length && i<63; i=i+2) {
            if (arr[i]>arr[i+1]) {
               if(arr[i]>max)
                max = arr[i];
            }
            else
            if(arr[i+1]>max)
                max = arr[i+1];
        }
        return max;

    }


    public static int[] median_sort(int []a, int[] b){
        int[]c= new int[a.length];
        int a1=0,b1=a.length-1;
        for (int i=0; i<c.length;i++)
            c[i]=Math.max(a[a1++],b[b1--]);
        return c;
    }


    public static int Max(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < 64 - 1 && i < arr.length - 1; i = i + 2) {
            if (arr[i] > arr[i + 1])
                if (max < arr[i])
                    max = arr[i];
                else if (arr[i + 1] > max)
                    max = arr[i + 1];
        }
        return max;
    }

}

