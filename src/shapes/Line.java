//Elad Vizenblit
package shapes;

import java.util.List;

/**
 * This is an implementation of Line.
 * @author Elad Vizenblit
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * Constructor.
     *
     * @param start The start point of the line.
     * @param end   The end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     *
     * @param x1 x of the start point of the line.
     * @param y1 y of the start point of the line.
     * @param x2 x of the end point of the line.
     * @param y2 y of the end point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Get the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2));
    }

    /**
     * Get the middle point of the line.
     *
     * @return point which represents the middle of the line
     */
    public Point middle() {
        double midX, midY;
        midX = (start.getX() + end.getX()) / 2;
        midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return point represents the start of the line
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return point represents the end of the line
     */
    public Point end() {
        return end;
    }


    /**
     * checks if a point is on segment.
     * @param check the point we want to check if on the segment
     * @return true means on segment, else false.
     */
    public boolean onSegment1(Point check) {
        //The function gets NaN in case it touches the edge of the segment, (NaN coordinate depends on vertex collided)
        // so I change value  because we know its on the line
        if (Double.isNaN(check.getX())) {
            check = new Point(this.start.getX(), check.getY());
        }
        if (Double.isNaN(check.getY())) {
            check = new Point(check.getX(), this.start.getY());
        }
        if (check.getX() <= Math.max(this.start.getX(), this.end.getX())
                && check.getX() >= Math.min(this.start.getX(), this.end.getX())
                && check.getY() <= Math.max(this.start.getY(), this.end.getY())
                && check.getY() >= Math.min(this.start.getY(), this.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * This method checks the orientation of a point in relation to a line.
     *
     * @param p1 point represents the beginning of the line
     * @param p2 point represents the end of the line
     * @param p3 point orientation to be checked
     * @return 1 for clockwise, -1 for counter clockwise , 0 for collinear
     */
    public int orientation(Point p1, Point p2, Point p3) {

        //calculate orientation
        double o = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX())
                - (p3.getY() - p2.getY()) * (p2.getX() - p1.getX());
        //clockwise returns 1
        if (o > 0) {
            return 1;
        }
        //collinear returns 0
        if (o == 0) {
            return 0;
        }
        //o<0, counter clockwise: returns -1
        return -1;
    }

    /**
     * Checks if two lines intersect.
     *
     * @param other the line we want to check whether it intersects with
     * @return true for intersects, otherwise false
     */
    public boolean isIntersecting(Line other) {

        //if one of the lines isn't set properly
        if (other == null || other.end == null || other.start == null || this.start == null
                || this.end == null) {
            return false;
        }
        //it's the same line so it isn't intersecting
        if (this.equals(other)) {
            return false;
        }

        //returns orientation of all lines
        int o1 = orientation(start, end, other.start);
        int o2 = orientation(start, end, other.end);
        int o3 = orientation(other.start, other.end, start);
        int o4 = orientation(other.start, other.end, end);

        //checks if intersects or not by using the orientation results.
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        //in case collinear
        if (o1 == 0 && this.onSegment1(other.start)) {
            return true;
        }
        if (o2 == 0 && this.onSegment1(other.end)) {
            return true;
        }
        if (o3 == 0 && other.onSegment1(this.start)) {
            return true;
        }
        if (o4 == 0 && other.onSegment1(this.end)) {
            return true;
        }

        //no intersection
        return false;
    }


    /**
     * Return the coordinate where the lines intersects.
     *
     * @param other the line we check if it intersects with
     * @return Point which represents the intersection coordinate, if there is no such a point, returns null.
     */
    public Point intersectionWith(Line other) {

        //if one of the lines isn't set properly
        if (other == null || other.start == null || other.end == null || this.start == null
                || this.end == null) {
            return null;
        }

        //slope of 'this'
        final double slopeL1 = (((end.getY()) - start.getY()) / (end.getX() - start.getX()));
        //slope of 'other'
        final double slopeL2 = ((other.end.getY() - other.start.getY())
                / (other.end.getX() - other.start.getX()));

        //if Lines aren't intersecting, returns NULL. otherwise from here on we know lines intersect or collinear
        //with at least one mutual point
        if (!isIntersecting(other)) {
            return null;
        }

        //in case line is just a single point. And we know from previous statement there's an intersection
        if (this.start.equals(this.end)) {
            return this.start;
        }
        //in case line is just a single point
        if (other.start.equals(other.end)) {
            return other.start;
        }

        //in case line is parallel to Y axis
        if (start.getX() - end.getX() == 0) {
            //calculates the y value of the intersection by the linear equation formula
            double intersectionY = slopeL2 * start.getX() - slopeL2 * other.start.getX() + other.start.getY();
            return new Point(start.getX(), intersectionY);
        }

        //in case the other line is parallel to Y axis.
        if (other.start.getX() - other.end.getX() == 0) {
            //calculates y value of the intersection by the linear equation formula
            double intersectionY = slopeL1 * other.start.getX() - slopeL1 * start.getX() + start().getY();
            return new Point(other.start.getX(), intersectionY);
        }

        //in case both lines are parallel to X axis
        if (slopeL1 == 0 && slopeL2 == 0) {
            double epsilon = Math.pow(10, -8);
            double maxThis = Math.max(this.start.getX(), this.end.getX());
            double minThis = Math.min(this.start.getX(), this.end.getX());
            double maxOther = Math.max(other.start.getX(), other.end.getX());
            double minOther = Math.min(other.start.getX(), other.end.getX());
            double maxXBetweenLines = Math.max(maxOther, maxThis);

            //it means they have multiple intersections because they have the same edge on one side.
            if (maxThis == maxOther || minThis == minOther) {
                return null;
            }

            //split to cases, first case 'this' is on the right side of 'other'. other case vice versa.
            if (maxXBetweenLines == maxThis) {
                //other is on the left, so if we subtract epsilon and we're still on minThis
                // we have multiple intersections
                if (maxOther - epsilon > minThis) {
                    return null;
                }
                return new Point(minThis, this.start.getY());
            }
            if (maxXBetweenLines == maxOther) {
                //same as before but vice versa
                if (maxThis - epsilon > minOther) {
                    return null;
                }
                return new Point(minOther, this.start.getY());
            }
        }


        //in case no line is parallel to Y axis. division by zero is impossible because it means line is parallel
        // to Y or X and we already checked this case.

        //calculates the intersection points by linear equation formula.
        double intersectionX = (slopeL1 * start.getX() - start.getY() - slopeL2 * other.start.getX()
                + other.start.getY()) / (slopeL1 - slopeL2);
        double intersectionY = slopeL1 * intersectionX - slopeL1 * start.getX() + start.getY();
        Point intersection = new Point(Math.round(intersectionX), Math.round(intersectionY));
        //in case there is no intersection, shouldn't happen but using as a coverage just in case.
        if (Double.isNaN(intersection.getX()) || Double.isNaN(intersection.getY())) {
            return null;
        }
        return intersection;
    }

    /**
     * Checks if lines are equal.
     *
     * @param other the line we want to check with if equal
     * @return True for equal, otherwise false
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        //checks if made of same points
        return ((start.getX() == other.start.getX() && start.getY() == other.start.getY()
                && end.getX() == other.end.getX() && end.getY() == other.end.getY())
                || (start.getX() == other.end.getX() && start.getY() == other.end.getY()
                && end.getX() == other.start.getX() && end.getY() == other.start.getY()));
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * Check what is the closest rectangle edge intersection with the start of the line of ball trajectory.
     * @param rect the rectangle we are checking with
     * @return the closest point to the ball trajectory with the rectangle
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //trajectory line
        Line l1 = new Line(this.start, this.end);
        List<Point> intersectionList = rect.intersectionPoints(l1);
        double min1 = 0;
        Point closest;
        if (l1 == null || intersectionList.size() == 0) {
            return null;
        } else {
            min1 = this.start.distance(intersectionList.get(0));
            closest = intersectionList.get(0);
            for (int i = 1; i < intersectionList.size(); i++) {
                if (min1 > this.start.distance(intersectionList.get(i))) {
                    min1 = this.start.distance(intersectionList.get(i));
                    closest = intersectionList.get(i);
                }
            }
            return closest;
        }
    }
}


