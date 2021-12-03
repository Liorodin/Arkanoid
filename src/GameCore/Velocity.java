package GameCore;

import Gui.geometry.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Leonardo Rodin 207377151
 * Gui.geometry.Velocity Class
 */
public class Velocity {

    // Finals
    private static final double TOP_SPEED = 90;

    // Fields
    private double dx;
    private double dy;

    // constructors

    /**
     * @param angle desired angel
     * @param speed desired speed
     * @return a new Gui.geometry.Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //sets delta x
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        //sets delta y
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @param dx delta x
     * @param dy delta y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * creates a random velocity.
     */
    public Velocity() {
        Random rand = new Random();
        dx = TOP_SPEED * rand.nextDouble();
        dy = TOP_SPEED * rand.nextDouble();
    }

    /**
     * @return velocity's speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * @return velocity's angle
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(dx, -dy));
    }

    /**
     * @return delta x
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return delta y
     */
    public double getDy() {
        return dy;
    }

    /**
     * sets a new value to dy.
     *
     * @param newDy new dy
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * sets a new value to DX.
     *
     * @param newDx new dx
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * @param p the point needed to be moved
     * @return p after moving it dx and dy
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * @param numOfVelocities the needed amount of velocities
     * @return returns a list of velocities.
     */
    public static List<Velocity> getListVelocities(int numOfVelocities) {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < numOfVelocities; i++) {
            velocityList.add(new Velocity((i - 2) * (Math.pow(-1, i)), 5));
        }
        return velocityList;
    }
}
