package ro.teamnet.zerotohero.oop.runapp;
import ro.teamnet.zerotohero.oop.graphicshape.*;
import ro.teamnet.zerotohero.canvas.Canvas;
/**
 * Created by user on 6/30/2016.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles circles = new Circles();
        Canvas canvas = new Canvas();
        circles.getAreaDef();

        Shape shape = new Circle(10);
        System.out.println(shape.area());

        ShapeBehaviour shape_behaviour = new Square(10);
        System.out.println(shape_behaviour.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

    }
}
