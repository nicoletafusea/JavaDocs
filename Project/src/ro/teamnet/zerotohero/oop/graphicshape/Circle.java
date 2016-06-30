package ro.teamnet.zerotohero.oop.graphicshape;
import java.lang.Math;
/**
 * Created by user on 6/30/2016.
 */
public class Circle extends Shape {
    private int xPos;
    private int yPos;
    private int radius;

    public Circle() {
        this.xPos = 0;
        this.yPos = 0;
        this.radius = 0;
    }

    public Circle(int x) {
        this();
        this.xPos = x;
    }

    public Circle(int x, int y) {
        this(x);
        this.yPos = y;
    }

    public Circle(int x, int y, int rad) {
        this(x, y);
        this.radius = rad;
    }

    public double area() {
        return radius*radius*Math.PI;
    }

    public String toString() {
        return "center = (" + this.xPos + "," + this.yPos + ") and radius = " + this.radius;
    }

    public void fillColour() {
        System.out.println(super.color);
    }

    public void fillColour(int c) {
        super.setColor(c);
        System.out.println("The circle color is now " + super.color);
    }

    public void fillColour(float s) {
        super.setSaturation(s);
        System.out.println("The circle saturation is now " + super.saturation);
    }
}
