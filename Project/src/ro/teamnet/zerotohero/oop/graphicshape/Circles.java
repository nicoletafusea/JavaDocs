package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 */
public class Circles {

    public double getAreaPub() {
        return new Circle().area();
    }

    public void getAreaDef() {
        Circle c = new Circle(5, 4, 10);
        c.fillColour();
        c.fillColour(4);
        c.fillColour(3.4f);
    }
}
