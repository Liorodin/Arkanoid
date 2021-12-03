package Gui.geometry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * rectangle shape class
 */
public class Rectangle {

    // Finals
    public static final int UPPER_SIDE = 0;
    public static final int LEFT_SIDE = 1;
    public static final int RIGHT_SIDE = 2;
    public static final int LOWER_SIDE = 3;

    // Fields
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Create a new rectangle with a location and width/height.
     *
     * @param upperLeft the point from which the rectangle starts
     * @param width     width of the rectangle
     * @param height    height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Create a new rectangle with a location and width/height.
     *
     * @param x      x coordinate of the upperLeft point of the rectangle
     * @param y      y coordinate of the upperLeft point of the rectangle
     * @param width  width of the rectangle
     * @param height height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;

    }

    /**
     * @return an array of the rectangle sides
     */
    public Line[] getRectangleSides() {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Line[] sidesOfRectangle = new Line[4];
        sidesOfRectangle[0] = new Line(upperLeft, upperRight);
        sidesOfRectangle[1] = new Line(upperLeft, lowerLeft);
        sidesOfRectangle[2] = new Line(upperRight, lowerRight);
        sidesOfRectangle[3] = new Line(lowerLeft, lowerRight);
        return sidesOfRectangle;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line we check for possible intersections
     * @return a list of points (possible intersections)
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> listOfPoints = new ArrayList<>();
        Line[] sidesOfRectangle = getRectangleSides();
        //checks intersections between the line and the sides of the rectangle
        for (Line side : sidesOfRectangle) {
            Point intersection = side.intersectionWith(line);
            //adds the point to the list if there's an intersection
            if (intersection != null) {
                listOfPoints.add(intersection);
            }
        }
        return listOfPoints;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @param newColor the color desired for the rectangle
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

}