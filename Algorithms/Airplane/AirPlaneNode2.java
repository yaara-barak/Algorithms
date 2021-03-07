package Airplane;

import java.util.LinkedList;

public class AirPlaneNode2 {

    private int right, down, min1, min2, countOfPath, valueA, valueB;
    private LinkedList<String> paths;

    public AirPlaneNode2(int r, int d) {
        this.right = r;
        this.down = d;
        this.min1 = 0;
        this.min2 = 0;
        this.countOfPath = 0;
        this.paths = new LinkedList<>();
        this.valueA = 0;
        this.valueB = 0;

    }

    public int getDown() {
        return down;
    }

    public int getRight() {
        return right;
    }

    public int getMin1() {
        return min1;
    }

    public int getMin2() {
        return min2;
    }

    public int getValueA() {
        return valueA;
    }

    public int getValueB() {
        return valueB;
    }

    public int getCountOfPath() {
        return countOfPath;
    }

    public void setMin1(int min1) {
        this.min1 = min1;
    }

    public void setMin2(int min2) {
        this.min2 = min2;
    }

    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    public void setCountOfPath(int countOfPath) {
        this.countOfPath = countOfPath;
    }

    public LinkedList<String> getPaths() {
        return paths;
    }

}



