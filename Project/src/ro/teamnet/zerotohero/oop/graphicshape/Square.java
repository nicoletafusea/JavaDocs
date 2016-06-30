package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 */
public class Square extends Shape {
    private int side;

    public Square(int s) {
        this.side = s;
    }

    public double area() {
        return this.side*this.side;
    }
}
