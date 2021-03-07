package Small_problems;

public class pizza {
    /**
     * returns the optimal division for the faster man
     * Complexity: O(1)
     */
    public static int getNumberOfPieces(double k) {
        if(k == (int)k) return (int)k+1;
        return (int)k+2;
    }

    public static void main(String[] args) {
        int number=2;
        System.out.println("The pizza should be devised to "+getNumberOfPieces(number)+" pieces");
    }
}
