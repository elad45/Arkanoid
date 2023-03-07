//Elad Vizenblit
package shapes;

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 */
public class Rectangle {

    private Point upperLeft;
    private final double width;
    private final double height;
    private Point bottomLeft;
    private Point bottomRight;
    private Point upperRight;
    private Line topHorizontal;
    private Line leftVertical;
    private Line rightVertical;
    private Line bottomHorizontal;

    /**
     * Constructor.
     *
     * @param upperLeft upper left (in the animation) vertex
     * @param width     the width of the rectangle
     * @param height    height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(bottomLeft.getX() + width, bottomLeft.getY());
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.topHorizontal = new Line(upperLeft, upperRight);
        this.leftVertical = new Line(upperLeft, bottomLeft);
        this.bottomHorizontal = new Line(bottomLeft, bottomRight);
        this.rightVertical = new Line(upperRight, bottomRight);
    }

    /**
     * changing the location of the rectangle.
     *
     * @param newUpperLeft sets the new location of the rectangle by the UpperLeft vertex
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * Returns all the intersection points between a line and the Recntangle.
     *
     * @param line the line that we check for intersection with the rectangle.
     * @return list of all the intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointsL = new ArrayList<Point>();

        //each condition checks for intersection with the written edge.
        if (line.intersectionWith(this.topHorizontal) != null) {
            intersectionPointsL.add(line.intersectionWith(this.topHorizontal));
        }
        if (line.intersectionWith(this.leftVertical) != null) {
            intersectionPointsL.add(line.intersectionWith(this.leftVertical));
        }

        if (line.intersectionWith(this.bottomHorizontal) != null) {
            intersectionPointsL.add(line.intersectionWith(this.bottomHorizontal));
        }

        if (line.intersectionWith(this.rightVertical) != null) {
            intersectionPointsL.add(line.intersectionWith(this.rightVertical));
        }
        return intersectionPointsL;
    }

    /**
     * getter Width.
     *
     * @return return rectnagle width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getter Height.
     *
     * @return return rectangle height
     */
    public double getHeight() {
        return this.height;
    }

    // Returns the vertices of the rectangle.

    /**
     * getter upper left vertex of the rectangle.
     *
     * @return upper left vertex of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getter upper right vertex of the rectangle.
     *
     * @return upper right vertex of the rectangle
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * getter bottom left vertex of the rectangle.
     *
     * @return bottom left vertex of the rectangle
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * getter bottom right vertex of the rectangle.
     *
     * @return bottom right vertex of the rectanlge
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }

    //returns the edge of the rectangle

    /**
     * getter top Horizontal Edge.
     *
     * @return top Horizontal edge
     */
    public Line getTopHorizontal() {
        return this.topHorizontal;
    }

    /**
     * getter left vertical edge.
     *
     * @return left vertical edge
     */
    public Line getLeftVertical() {
        return this.leftVertical;
    }

    /**
     * getter bottom horizontal edge.
     *
     * @return bottom horizontal edge.
     */
    public Line getBottomHorizontal() {
        return this.bottomHorizontal;
    }

    /**
     * getter right vertical edge.
     *
     * @return right vertical edge.
     */
    public Line getRightVertical() {
        return this.rightVertical;
    }
}
