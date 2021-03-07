package Small_problems;

import java.util.Arrays;

public class secretary {
    public static double getAvarageTime(int[] times) {
        Arrays.sort(times);
        double avg = 0;
        for(int x : times)
            avg = avg + avg + x;
        return avg/times.length;
    }

    public static void main(String[] args) {
        int[] arr= {3,50,9,87,4};
        System.out.println("The average time is "+getAvarageTime(arr));
    }
}
