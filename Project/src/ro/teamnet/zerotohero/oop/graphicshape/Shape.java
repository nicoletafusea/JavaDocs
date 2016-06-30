package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 */
public class Shape extends AbstractShape implements ShapeBehaviour {

    protected int color;
    protected float saturation;

    public double area() {
        return 2.3;
    }

    public void setColor(int c) {
        this.color = c;
    }

    public void setSaturation(float sat) {
        this.saturation = sat;
    }
}
