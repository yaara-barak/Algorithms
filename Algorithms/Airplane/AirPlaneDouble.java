package Airplane;

public class AirPlaneDouble {
    double price, right, down;

    public AirPlaneDouble(double x, double y) {
        this.right = x;
        this.down = y;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
    }
}
