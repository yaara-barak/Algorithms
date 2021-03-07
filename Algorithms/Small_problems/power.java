package Small_problems;

public class power {
    /**
     * returns x^n
     * Complexity: O(log n)
     */
    public static double powInd(double x, int n) {
        double ans = 1;
        while(n != 0) {
            if(n % 2 == 1)
                ans = ans * x;
            x = x*x;
            n = n / 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(powInd(7,3));
    }

}
