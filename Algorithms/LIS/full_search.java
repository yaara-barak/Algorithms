package LIS;
import java.util.ArrayList;



public class full_search {
    public static void main(String[] args) {
        int arr[] = {0, 8, 4, 12, 2, 2, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        //{0, 8, 4, 15, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 12};

        //FullSearch
        LIS_FullSearch(arr);

    }
    private static void LIS_FullSearch(int[] arr) {
        int[] binaryArray = new int[arr.length];
        int interations = (int)Math.pow(2, arr.length)-1;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        boolean check;
        int lis = 0;
        ArrayList<Integer> longestSubArray = new ArrayList<Integer>();
        for (int i = 0; i < interations; i++) {
            Plus1(binaryArray);
            temp.clear();

            //copy elements with '1' in the binary array
            for (int j = 0; j < binaryArray.length; j++) {
                if (binaryArray[j]==1)
                    temp.add(arr[j]);
            }
            int x = 0;
            if (i == 249)
            {
                x = 8;
            }
            //check if increasing
            check = true;
            for (int j = 1; j < temp.size() && check == true; j++) {
                if (temp.get(j) < temp.get(j-1))
                    check = false;
            }

            if (check==true && temp.size()>lis)
            {
                lis = temp.size();
                longestSubArray.clear();
                for (int j = 0; j < temp.size(); j++) {
                    longestSubArray.add(temp.get(j));
                }
            }
        }
        System.out.println("Full Search: LIS = " + lis + ", increasing subsequence = " + longestSubArray.toString());
    }
    private static void Plus1(int[] array) {
        boolean flag = true;
        int i = array.length-1;
        while(flag == true)
        {
            if (array[i] == 0)
            {
                array[i] = 1;
                flag = false;
            }
            else
            {
                array[i] = 0;
                i--;
            }
        }
    }
}
