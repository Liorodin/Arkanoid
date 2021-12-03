package Gui.geometry;

import GameCore.Velocity;

import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * two dimensional Gui.geometry.Line Class
 */
public class Line {

    // Finals
    private final double portion = 0.5;

    // Fields
    private Point start;
    private Point end;
    private Function func;

    // constructors

    /**
     * @param start the staring point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        func = new Function(start, end);
    }

    /**
     * @param x1 the x coordinate of the starting point
     * @param y1 the y coordinate of the starting point
     * @param x2 the x coordinate of the ending point
     * @param y2 the y coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        func = new Function(start, end);
    }

    // random Gui.geometry.Line

    /**
     * creates a random line.
     */
    public Line() {
        //create a random starting point
        this.start = new Point();
        //create a random ending point
        this.end = new Point();
        func = new Function(start, end);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double x, y;
        x = (start.getX() + end.getX()) / 2;
        y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * @return end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * @return the function of the line
     */
    public Function func() {
        return func;
    }

    /**
     * gets a point and checks if the point is on this line.
     *
     * @param point another point
     * @return true if the point is on this line, false otherwise
     */
    public boolean isPointOnLine(Point point) {
        if (Double.isInfinite(func().getCurvature())) {
            return this.start().getX() == point.getX() && middle().distance(point) <= length() / 2;
        }
        double y = this.func.getY(point.getX());
        Point newPoint = new Point(point.getX(), y);
        return newPoint.equals(point) && (middle().distance(newPoint) <= length() / 2);
    }

    /**
     * @return the beginning of the line
     */
    private Point beginning() {
        //checks which x is smaller
        if (start.getX() != end.getX()) {
            if (start.getX() < end.getX()) {
                return start;
            }
            return end;
        }
        //checks which y is smaller
        if (start.getY() < end.getY()) {
            return start;
        }
        return end;
    }

    /**
     * @return the ending of the line
     */
    private Point ending() {
        //checks who is the beginning
        if (start.equals(beginning())) {
            return end;
        }
        return start;
    }

    /**
     * @param other another line
     * @return true if the this line intertwines with other line,
     * false otherwise
     */
    private boolean isIntertwine(Line other) {
        //checks if the lines are equal and that this line isn't a dot
        if (this.equals(other) && !start.equals(end)) {
            return true;
        }
        //compares between curvatures
        if (this.func().getCurvature() == other.func().getCurvature()) {
            //checks if the line have one connecting point
            if (this.beginning().equals(other.ending()) || other.beginning().equals(this.ending())) {
                return false;
            }

            /*
             * checks if the starting or ending point of this line is on the
             * other line and vice versa
             */
            return this.isPointOnLine(other.beginning()) || this.isPointOnLine(other.ending())
                    || other.isPointOnLine(this.beginning()) || other.isPointOnLine(this.ending());
        }
        return false;
    }

    /**
     * @param other another line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //checks if the lines intertwine
        if (this.isIntertwine(other)) {
            return true;
        }
        //checks if both lines are dots and are equal
        if (start.equals(end) && other.start().equals(other.end()) && start.equals(other.start())) {
            return true;
        }
        //checks if one of the lines is a dot
        if (this.start.equals(this.end) || other.start().equals(other.end())) {
            //checks if the dot is on the other line
            return isPointOnLine(other.start()) || other.isPointOnLine(start);
        }
        //checks if both lines have the same curvature
        if (this.func.getCurvature() == other.func().getCurvature()) {
            //returns if the lines have a connecting point
            return (this.ending().equals(other.beginning()) || this.beginning().equals(other.ending()));
        }
        //checks if one of the lines is vertical
        if (Double.isInfinite(this.func.getCurvature()) || Double.isInfinite(other.func().getCurvature())) {
            //gets the y for the other starting point x
            double y1 = this.func.getY(other.start().getX());
            //gets the y for the this starting point x
            double y2 = other.func().getY(this.start.getX());
            //creates two points
            Point point1 = new Point(other.start().getX(), y1);
            Point point2 = new Point(this.start.getX(), y2);
            //returns if one of the points is on the other line
            return (this.isPointOnLine(point1) && other.isPointOnLine(point1)) || (this.isPointOnLine(point2)
                    && other.isPointOnLine(point2));
        }
        //gets the intersection point of the lines
        Point point = this.func().intersection(other.func());
        //return if the point is on both lines
        return this.isPointOnLine(point) && other.isPointOnLine(point);
    }

    /**
     * @param other another line
     * @return the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //checks if the lines intersect and not intertwine
        if (this.isIntersecting(other) && !this.isIntertwine(other)) {
            //checks if this line is a dot
            if (this.start.equals(this.end)) {
                return this.start;
            }
            //checks if the other line is a dot
            if (other.start().equals(other.end())) {
                return other.start();
            }
            //checks if both line have the same curvature
            if (this.func.getCurvature() == other.func().getCurvature()) {
                //checks if the other line begins where this line ends
                if (this.ending().equals(other.beginning())) {
                    return this.ending();
                }
                return this.beginning();
            }
            //checks if this line is vertical
            if (Double.isInfinite(this.func.getCurvature())) {
                return new Point(start.getX(), other.func().getY(start.getX()));
            }
            //checks if the other line is vertical
            if (Double.isInfinite(other.func().getCurvature())) {
                return new Point(other.start().getX(), this.func.getY(other.start().getX()));
            }
            //returns the intersection point of the lines
            return func.intersection(other.func());
        }
        return null;
    }

    /**
     * compares between this line and the other line.
     *
     * @param other another line
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.beginning().equals(other.beginning()) && this.ending().equals(other.ending());
    }

    /**
     * @param rect the tested rectangle
     * @return return the closest intersection point to the start of the line, null if there is not
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        //if there's only one point in the list return it
        if (intersections.size() != 0) {
            Point nearestPoint = intersections.get(0);
            for (int i = 1; i < intersections.size(); i++) {
                Point newPoint = intersections.get(i);
                if (start.distance(newPoint) < start.distance(nearestPoint)) {
                    nearestPoint = newPoint;
                }
            }
            return nearestPoint;
        }
        return null;
    }

    /**
     * @param collisionPoint the collision at the end of the line
     * @param velocity       the velocity of the object moving on the line
     * @return return a point near to the collisionPoint
     */
    public Point nearCollision(Point collisionPoint, Velocity velocity) {
        Line impactRoot = new Line(this.start, collisionPoint);
        //calculating the ration between the impact root with the root he could have done without obstacles
        double ratio = portion * impactRoot.length() / this.length();
        //gives the coordinates a portion of the real speed
        return new Point(start.getX() + velocity.getDx() * ratio, start.getY() + velocity.getDy() * ratio);
    }
}

