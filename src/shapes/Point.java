//Elad Vizenblit
package shapes;


/**
 * This is class Point.
 * @author Elad Vizenblit
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x x value of the point
     * @param y y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates distance between two points.
     * @param other the point to check distance with
     * @return the distance between two points.
     */
    public double distance(Point other) {
        if (other == null) {
            return 0;
        }
        return Math.sqrt(Math.pow((this.y - other.getY()), 2) + Math.pow((this.x - other.getX()), 2));
    }

    /**
     * Checks if two points are equal.
     * @param other the point to check equal with
     * @return True for equal, otherwise false.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(10, -8);
        return ((Math.abs(this.getX() - other.getX()) < epsilon) && (Math.abs(this.getY() - other.getY()) < epsilon));
    }

    /**
     *Get X value of the point.
     * @return X value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get Y value of the point.
     * @return Y value of the point
     */
    public double getY() {
        return this.y;
    }
}

