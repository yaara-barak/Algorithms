package Airplane;

public class Point implements Comparable<Point> {
    int i;
    int j;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(Point o) {
        if (this.i > o.i)
            return 1;
        else if (this.i == o.i && this.j > o.j)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "( " + i +
                ", " + j +
                ')';
    }
}
