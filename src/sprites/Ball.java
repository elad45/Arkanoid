//Elad Vizenblit
package sprites;

import biuoop.DrawSurface;
import collidables.CollisionInfo;
import gameConfig.GameEnvironment;
import gameConfig.GameLevel;
import gameConfig.Velocity;
import interfaces.Sprite;
import shapes.Line;
import shapes.Point;

import java.awt.*;
/**
 * This is a Ball (2D) class.
 * @author Elad Vizenblit
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;

    /**
     * Constructor.
     * @param center center of the ball.
     * @param r radius of the ball
     * @param color color of the ball
     * @param game game environment we want to have it in.
     */
    public Ball(Point center, int r, Color color, GameEnvironment game) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.game = game;
    }

    /**
     * Constructor.
     * @param center center coordinate of the ball
     * @param r radius of the ball
     * @param color color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor.
     * @param x x of the center
     * @param y y of the center
     * @param r radius of the ball
     * @param color color of the ball
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Gets X coordinate of the center.
     * @return int x coordinate represents the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets Y coordinate of the center.
     * @return  int y coordinate represents the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the radius of the ball.
     * @return int represents radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets the color of the ball.
     * @return color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     * @param surface The surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) this.center.getY(), radius);
    }

    /**
     * Set velocity to the ball.
     * @param v velocity to be set on the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set velocity to the ball.
     * @param dx velocity x axis
     * @param dy velocity in y axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the ball velocity.
     * @return Velocity indicates the ball velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Makes a ball single move. in case the ball touches two edges it changes vertical and horizontal directions.
     */
    public void moveOneStep() {
        Line ballTrajectory = ballMovement();
        CollisionInfo objectCollided = this.game.getClosestCollision(ballTrajectory);

        //in case no velocity is set
        if (this.getVelocity() == null) {
            return;
        }
        //in case no object interfering
        if (objectCollided == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Line topCollisionRecEdge = objectCollided.collisionObject().getCollisionRectangle().getTopHorizontal();
            Line leftCollisionRecEdge = objectCollided.collisionObject().getCollisionRectangle().getLeftVertical();
            Line bottomCollisionRecEdge = objectCollided.collisionObject().getCollisionRectangle().
                    getBottomHorizontal();
            Line rightCollisionRecEdge = objectCollided.collisionObject().getCollisionRectangle().getRightVertical();

            //the following four conditions are for intersections with the vertices.
            //if collided with upper left vertex
            if (topCollisionRecEdge.onSegment1(objectCollided.collisionPoint())
                    && (leftCollisionRecEdge.onSegment1(objectCollided.collisionPoint()))) {
                this.velocity = objectCollided.collisionObject().hit(this, objectCollided.collisionPoint(),
                        this.getVelocity());
                return;
            }
            //if collided with upper right vertex
            if (topCollisionRecEdge.onSegment1(objectCollided.collisionPoint())
                    && rightCollisionRecEdge.onSegment1(objectCollided.collisionPoint())) {
                this.velocity = objectCollided.collisionObject().hit(this, objectCollided.collisionPoint(),
                        this.getVelocity());
                return;
            }
            //if collided with bottom left vertex
            if (bottomCollisionRecEdge.onSegment1(objectCollided.collisionPoint())
                    && (leftCollisionRecEdge.onSegment1(objectCollided.collisionPoint()))) {
                this.velocity = objectCollided.collisionObject().hit(this, objectCollided.collisionPoint(),
                        this.getVelocity());
                return;
            }
            //if collided with bottom right vertex
            if (bottomCollisionRecEdge.onSegment1(objectCollided.collisionPoint())
                    && (rightCollisionRecEdge.onSegment1(objectCollided.collisionPoint()))) {
                this.velocity = objectCollided.collisionObject().hit(this, objectCollided.collisionPoint(),
                        this.getVelocity());
                return;
            }

            //regular conditions to check one edge collision.

            //collision occured on the top segment.
            if (topCollisionRecEdge.onSegment1(objectCollided.collisionPoint())) {
                this.center = new Point(this.getX(), this.getY() - 1);
                this.velocity = objectCollided.collisionObject().hit(this,
                        objectCollided.collisionPoint(), this.getVelocity());
            }
            //collision occured on left vertical.
            if (leftCollisionRecEdge.onSegment1(objectCollided.collisionPoint())) {
                this.center = new Point(this.getX() - 1, this.getY());
                this.velocity = objectCollided.collisionObject().hit(this,
                        objectCollided.collisionPoint(), this.getVelocity());
            }
            //collision occured on bottom horizontal.
            if (bottomCollisionRecEdge.onSegment1(objectCollided.collisionPoint())) {
                this.center = new Point(this.getX(), this.getY() + 1);
                this.velocity = objectCollided.collisionObject().hit(this,
                        objectCollided.collisionPoint(), this.getVelocity());
            }

            //collision occured on right vertical segment
            if (rightCollisionRecEdge.onSegment1(objectCollided.collisionPoint())) {
                this.center = new Point(this.getX() + 1, this.getY());
                this.velocity = objectCollided.collisionObject().hit(this,
                        objectCollided.collisionPoint(), this.getVelocity());
            }
        }
    }


    /**
     * Return a line of the ball movement in this step. This is the ball trajectory.
     * @return Line , start is current position end is where it gonna be after this step.
     */
    public Line ballMovement() {
        return new Line(this.center, velocity.applyToPoint(this.center));
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }


    /**
     * Adds ball to the game.
     * @param g the game we want to have it in
     */
    public void addToGame(GameLevel g) {
       if (g != null) {
           g.addSprite(this);
       }
    }

    /**
     * Removes sprite from the game.
     * @param g the game we are playing in
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}
