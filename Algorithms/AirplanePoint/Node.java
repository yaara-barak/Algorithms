package AirplanePoint;

public class Node {
    double x, y,price,price2,paths;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y +
                ", price=" + price
                +", price2=" + price2
                + ", paths="+ paths;
    }
}

