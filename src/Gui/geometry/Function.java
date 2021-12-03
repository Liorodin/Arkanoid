package Gui.geometry;

/**
 * @author Leonardo Rodin 207377151
 * Gui.geometry.Function Class
 */
public class Function {

    // Fields
    private double curvature;
    private double constant;

    // constructors

    /**
     * @param line the line of which the function will be built on
     */
    public Function(Line line) {
        curvature = (line.end().getY() - line.start().getY()) / (line.end().getX() - line.start().getX());
        constant = line.start().getY() - line.start().getX() * curvature;
    }

    /**
     * @param start the starting point line of which the function will
     *              be built on
     * @param end   the ending point line of which the function will
     *              be built on
     */
    public Function(Point start, Point end) {
        curvature = (end.getY() - start.getY()) / (end.getX() - start.getX());
        if (Double.isInfinite(curvature) && curvature < 0) {
            curvature = -curvature;
        }
        constant = start.getY() - start.getX() * curvature;
    }

    /**
     * @return the constant of the function
     */
    public double getConstant() {
        return constant;
    }

    /**
     * @return the curvature of the function
     */
    public double getCurvature() {
        return curvature;
    }

    /**
     * gets an x and returns his "picture".
     *
     * @param x a number
     * @return the result of x
     */
    public double getY(double x) {
        return curvature * x + constant;
    }

    /**
     * compares between this function and the other for an intersection point.
     *
     * @param other a second function
     * @return the intersection point
     */
    public Point intersection(Function other) {
        //checks if both function have the same curvature
        if (this.curvature == other.getCurvature()) {
            return null;
        }
        //gets the x coordinate of the intersection
        double x = (other.getConstant() - this.constant) / (this.curvature - other.getCurvature());
        //gets the y coordinate of the intersection
        double y = curvature * x + constant;
        //returns the intersection point
        return new Point(x, y);
    }
}

