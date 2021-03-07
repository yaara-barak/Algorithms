package AirplanePoint;

import Airplane.AirPlaneDouble;
import Airplane.AirPlaneNode;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Point forbidden[] = {new Point(1, 1), new Point(2, 3)};
        Node[][] mat = initMatOfNodes();
        System.out.println(numOfCheapestPaths(mat, forbidden));
    }

    public static Node[][] initMatOfNodes() { // n = 4
        int n = 4;
        Node mat[][] = new Node[n][n];
        //the 1-st row
        mat[0][0] = new Node(1, 3);
        mat[0][1] = new Node(8, 4);
        mat[0][2] = new Node(3, 8);
        mat[0][3] = new Node(0, 4);
        //the 2-nd row
        mat[1][0] = new Node(2, 5);
        mat[1][1] = new Node(5, 11);
        mat[1][2] = new Node(3, 1);
        mat[1][3] = new Node(0, 2);
        //the 3-d row
        mat[2][0] = new Node(4, 10);
        mat[2][1] = new Node(3, 1);
        mat[2][2] = new Node(1, 4);
        mat[2][3] = new Node(0, 8);
        //the 4-th row
        mat[3][0] = new Node(2, 0);
        mat[3][1] = new Node(3, 0);
        mat[3][2] = new Node(5, 0);
        mat[3][3] = new Node(0, 0);
        return mat;
    }

    public static Node[][] initDed(Node[][] mat, Point forbidden[]) {
        int n = mat.length;
        int m = mat[0].length;
        Node[][] newMat = new Node[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i >= forbidden[0].x() && i <= forbidden[1].x() && j >= forbidden[0].y() && j <= forbidden[1].y()) {
                    newMat[i][j] = new Node(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                } else {
                    newMat[i][j] = new Node(mat[i][j].x, mat[i][j].y);
                }
            }
        }
        return newMat;

    }

    public static void ShortestPath(Node[][] nodes, Point forbidden[]) {
        Node[][] mat = initDed(nodes, forbidden);
        mat[0][0].price = 0;
        for (int i = 1; i < mat.length; i++)
            mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;

        for (int j = 1; j < mat[0].length; j++)
            mat[0][j].price = mat[0][j - 1].price + mat[0][j - 1].x;

        for (int i = 1; i < mat.length; i++)
            for (int j = 1; j < mat[0].length; j++)
                mat[i][j].price = Math.min((mat[i][j - 1].price + mat[i][j - 1].x), (mat[i - 1][j].price + mat[i - 1][j].y));
        //Print(mat);
        System.out.println(mat[mat.length - 1][mat[0].length - 1].price);
    }

    private static void Print(Node[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j].toString() + "\t");
            }
            System.out.println();
        }
    }

    public static void SecondShortestPath(Node[][] nodes, Point forbidden[]) {
        Node[][] mat = initDed(nodes, forbidden);
        mat[0][0].price = 0;
        mat[0][0].price2 = 0;
        for (int i = 1; i < mat.length; i++) {
            mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;
            mat[i][0].price2 = mat[i - 1][0].price2 + mat[i - 1][0].y;
        }
        for (int j = 1; j < mat[0].length; j++) {
            mat[0][j].price = mat[0][j - 1].price + mat[0][j - 1].x;
            mat[0][j].price2 = mat[0][j - 1].price2 + mat[0][j - 1].x;
        }
        for (int i = 1; i < mat.length; i++)
            for (int j = 1; j < mat[0].length; j++) {
                double py = mat[i - 1][j].price + mat[i - 1][j].y;
                double p2y = mat[i - 1][j].price2 + mat[i - 1][j].y;
                double px = mat[i][j - 1].price + mat[i][j - 1].x;
                double p2x = mat[i][j - 1].price2 + mat[i][j - 1].x;
                double[] sorted = min4(py, p2y, px, p2x);
                mat[i][j].price = sorted[0];
                mat[i][j].price2 = sorted[1];
            }
        System.out.println(mat[mat.length - 1][mat[0].length - 1].price2);
    }

    private static double[] min4(double x1, double x2, double x3, double x4) {
        double[] ans = {x1, x2, x3, x4};
        Arrays.sort(ans);
        if (ans[0] == ans[1]) {
            if (ans[0] == ans[2])
                ans[1] = ans[3];
            else
                ans[1] = ans[2];
        }
        return ans;
    }

    public static double numOfCheapestPaths(Node[][] nodes, Point forbidden[]) {
        Node[][] mat = initDed(nodes, forbidden);
        mat[0][0].price = 0;
        for (int i = 1; i < mat.length; i++) {
            mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;
            mat[i][0].paths = 1;
        }
        for (int j = 1; j < mat[0].length; j++) {
            mat[0][j].price = mat[0][j - 1].price + mat[0][j - 1].x;
            mat[0][j].paths = 1;
        }
        for (int i = 1; i < mat.length; i++)
            for (int j = 1; j < mat[0].length; j++) {
                mat[i][j].price = Math.min((mat[i][j - 1].price + mat[i][j - 1].x), (mat[i - 1][j].price + mat[i - 1][j].y));
                double x = mat[i][j - 1].price + mat[i][j - 1].x;
                double y = mat[i - 1][j].price + mat[i - 1][j].y;
                if (x > y)
                    mat[i][j].paths = mat[i - 1][j].paths;
                else if (y > x)
                    mat[i][j].paths = mat[i][j - 1].paths;
                else
                    mat[i][j].paths=mat[i - 1][j].paths+mat[i][j - 1].paths;
            }
        return mat[mat.length - 1][mat[0].length - 1].paths;
    }


}
