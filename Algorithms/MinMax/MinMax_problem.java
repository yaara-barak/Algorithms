package MinMax;

public class MinMax_problem {

    public static void main(String[] args) {

        //create array
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        //find minmax - algorithm5
        MinMax5(arr);
    }


    private static void MinMax5(int[] arr) { // 3n/2 - 2
        int min, max;
        if (arr[0] < arr[1]) {
            min = arr[0];
            max = arr[1];
        } else {
            max = arr[0];
            min = arr[1];
        }
        for (int i = 2; i < arr.length - 1; i = i + 2) {
            if (arr[i] < arr[i + 1]) {
                if (arr[i] < min)
                    min = arr[i];
                if (arr[i + 1] > max)
                    max = arr[i + 1];
            } else {
                if (arr[i] > max)
                    max = arr[i];
                if (arr[i + 1] < min)
                    min = arr[i + 1];
            }
        }
        if (arr.length % 2 == 1) {
            int last = arr[arr.length - 1];
            if (last < min)
                min = last;
            else if (last > max)
                max = last;
        }
        System.out.println("Min = " + min);
        System.out.println("Max = " + max);


    }

}

