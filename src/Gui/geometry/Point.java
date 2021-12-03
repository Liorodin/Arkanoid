package Gui.geometry;

import GameAnimation.Zone;

import java.util.Random;

/**
 * @author Leonardo Rodin 207377151
 * one dimensional Gui.geometry.Point Class
 */
public class Point {

    // Finals
    private static final double EPSILON = Math.pow(10, -10);

    // Fields
    private double x;
    private double y;

    // constructors

    /**
     * creates a point in a specific zone.
     *
     * @param start  the starting boundaries of the point
     * @param end    the ending boundaries of the point
     * @param length additional length that needs to be considered
     * @return a point in a specific zone
     */
    public static Point createInZone(Point start, Point end, int length) {
        Random rand = new Random();
        double x = (end.getX() - start.getX() - 2 * length) * rand.nextDouble() + (start.getX() + length);
        double y = (end.getY() - start.getY() - 2 * length) * rand.nextDouble() + (start.getY() + length);
        return new Point(x, y);
    }

    /**
     * @param x x coordinate of the center of the ball
     * @param y y coordinate of the center of the ball
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * creates a random point.
     */
    public Point() {
        Random rand = new Random();
        x = Zone.WIDTH * rand.nextDouble();
        y = Zone.HEIGHT * rand.nextDouble();
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.getX() - other.getX()), 2) + Math.pow((this.getY() - other.getY()), 2));
    }

    /**
     * compares coordinates between points.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.getX()) <= EPSILON && Math.abs(this.y - other.getY()) <= EPSILON;
    }

    /**
     * @return the x values of this point
     */
    public double getX() {
        return x;
    }

    /**
     * sets a new x coordinate to point.
     *
     * @param newX the new x coordinate
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * @return the y values of this point
     */
    public double getY() {
        return y;
    }

    /**
     * sets a new y coordinate to point.
     *
     * @param newY the new y coordinate
     */
    public void setY(double newY) {
        this.y = newY;
    }
}