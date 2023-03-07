//Elad Vizenblit
package gameConfig;

import shapes.Point;
/**
 * This is Velocity class. gives 2D direction in 'x' and 'y' axes
 * @author Elad Vizenblit
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx speed in x axis
     * @param dy speed in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor.
     * @param velocity Point indicates the speed in x,y axes
     */
    public Velocity(Point velocity) {
        this.dx = velocity.getX();
        this.dy = velocity.getY();
    }

    /**
     * Set new velocity.
     * @param axisX new speed for x axis
     * @param axisY new speed for y axis
     * @return new Velocity
     */
    public Velocity setVelocity(double axisX, double axisY) {
        this.dx = axisX;
        this.dy = axisY;
        return new Velocity(this.dx, this.dy);
    }
    /**
     * Get the velocity in x axis.
     * @return double represents velocity in x axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get the velocity in y axis.
     * @return double represents the velocity in y axis
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Makes a move for the ball.
     * @param p Point indicates current position (x,y)
     * @return Point after a move with position (x+xy, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * makes a move for the ball using a vector.
     * @param angle the angle we want to move in given in degrees.
     * @param speed the speed we want to make a move with
     * @return Velocity the velocity of the ball (dx,dy) dx is velocity in x axis, dy is velocity in y axis
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * (-1) * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}