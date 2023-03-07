//Elad Vizenblit
package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameConfig.GameLevel;
import gameConfig.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

import java.awt.*;

/**
 * This class is the paddle of the game.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final Rectangle topEdge;
    private final Rectangle leftEdge;
    private final Rectangle bottomEdge;
    private final Rectangle rightEdge;
    private Block paddle;
    private Velocity velocity;
    private Color color;
    private Velocity paddleLeftVelocity;
    private Velocity paddleRightVelocity;
    private final int paddleWidth;


    static final int PADDLE_WIDTH = 100;
    static final int PADDLE_HEIGHT = 20;
    static final int SLIGHTLY_CHANGE = 30;
    static final int SIGNIFICANT_CHANGE = 60;
    static final Velocity PADDLE_LEFT_VELOCITY = new Velocity(-7, 0);
    static final Velocity PADDLE_RIGHT_VELOCITY = new Velocity(7, 0);

    /**
     * Constructor.
     *
     * @param topEdge    Rectangle top horizontal frame of the screen (in case we will want the paddle to also move up)
     * @param leftEdge   left vertical edge of the screen
     * @param bottomEdge bottom edge (also in case we will want to move the paddle vertical)
     * @param rightEdge  right vertical edge of the screen
     * @param keyboard   keyboard sensor.
     * @param speed speed of the paddle.
     * @param width width of the paddle.
     */
    public Paddle(Rectangle topEdge, Rectangle leftEdge, Rectangle bottomEdge, Rectangle rightEdge,
                  KeyboardSensor keyboard, int speed, int width) {
        this.topEdge = topEdge;
        this.leftEdge = leftEdge;
        this.bottomEdge = bottomEdge;
        this.rightEdge = rightEdge;
        this.paddle = new Block(new Rectangle(new Point(320, bottomEdge.getUpperLeft().getY() - PADDLE_HEIGHT),
                width, PADDLE_HEIGHT), Color.YELLOW);
        this.keyboard = keyboard;
        color = Color.YELLOW;
        this.paddleRightVelocity = new Velocity(speed, 0);
        this.paddleLeftVelocity = new Velocity(speed * (-1), 0);
        this.paddleWidth = width;
    }

    /**
     * moves the paddle to the left.
     */
    public void moveLeft() {
        //set paddle to move left.
        this.velocity = paddleLeftVelocity;

        //new Rectangle location
        Point newUpperLeft = velocity.applyToPoint(this.paddle.getCollisionRectangle().getUpperLeft());
        //in case went out of borders
        if (newUpperLeft.getX() < this.leftEdge.getBottomRight().getX()) {
            paddle.getCollisionRectangle().setUpperLeft(new Point(this.leftEdge.getBottomRight().getX(),
                    newUpperLeft.getY()));
            /*if ball stack in paddle - use this remarked method instead of the line above
            this.paddle = new Block(new Rectangle(new Point(this.leftEdge.getBottomRight().getX(),
            newUpperLeft.getY()), PADDLE_WIDTH, PADDLE_HEIGHT), Color.YELLOW);*/
        } else {
            this.paddle = new Block(new Rectangle(newUpperLeft, paddleWidth, PADDLE_HEIGHT), this.color);
        }
    }

    /**
     * Moves paddle to the right.
     */
    public void moveRight() {
        //set paddle to move right
        this.velocity = paddleRightVelocity;

        //new Rectangle location
        Point newUpperLeft = velocity.applyToPoint(this.paddle.getCollisionRectangle().getUpperLeft());
        //checks if new point is out of border, in case it is - set it to the rightmost possible
        if (newUpperLeft.getX() + paddleWidth > this.rightEdge.getBottomLeft().getX()) {
            //this.paddle = new Block(new Rectangle(new Point(this.rightEdge.getBottomLeft().getX() - PADDLE_WIDTH,
            // newUpperLeft.getY()), PADDLE_WIDTH, PADDLE_HEIGHT), Color.YELLOW);
            //if ball stack in paddle, use the function above.
            this.paddle.getCollisionRectangle().setUpperLeft(new Point(this.rightEdge.getBottomLeft().getX()
                    - paddleWidth, newUpperLeft.getY()));
        } else {
            this.paddle = new Block(new Rectangle(newUpperLeft, paddleWidth, PADDLE_HEIGHT), this.color);
        }
    }

    // Sprite

    /**
     * Checks whether right key, left key or nothing is pressed for moving the paddle.
     */
    public void timePassed() {
        if (this.keyboard == null) {
            return;
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * Draws the paddle.
     *
     * @param d surface we want to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddle.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.paddle.getCollisionRectangle().getUpperLeft().getY(), paddleWidth, PADDLE_HEIGHT);
    }


    // Collidable

    /**
     * checks which block is hit by the ball.
     *
     * @return the Rectangle that hit by the ball.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * sets new speed to the ball as it hits the paddle, as closer to the edge of the paddle.
     * the more extreme return angle.
     *
     * @param hitter          the hitting ball.
     * @param collisionPoint  The collision point.
     * @param currentVelocity Current velocity of the ball.
     * @return new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] segments = new Line[5];
        Point leftPointSegment = this.paddle.getCollisionRectangle().getUpperLeft();
        double speedUnit = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + (Math.pow(currentVelocity.getDy(), 2)));

        //in case hits left vertical sides of the paddle
        if (this.paddle.getCollisionRectangle().getLeftVertical().onSegment1(collisionPoint)
                || this.paddle.getCollisionRectangle().getRightVertical().onSegment1(collisionPoint)) {
            return currentVelocity.setVelocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        }

        //in case hits top horizontal segments

        //splitting the paddle to 5 equal segments. , 0 is the leftmost side.
        for (int i = 0; i < 5; i++) {
            Point rightPointSegment = new Point(leftPointSegment.getX() + (paddleWidth / 5),
                    this.paddle.getCollisionRectangle().getUpperLeft().getY());
            segments[i] = new Line(leftPointSegment, rightPointSegment);
            leftPointSegment = rightPointSegment;
        }
        //sets new speeds
        if (segments[0].onSegment1(collisionPoint)) {
            currentVelocity = Velocity.fromAngleAndSpeed(SIGNIFICANT_CHANGE * (-1), speedUnit);
        }
        if (segments[1].onSegment1(collisionPoint)) {
            currentVelocity = Velocity.fromAngleAndSpeed(SLIGHTLY_CHANGE * (-1), speedUnit);
        }
        //same as a block
        if (segments[2].onSegment1(collisionPoint)) {
            currentVelocity.setVelocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        }
        if (segments[3].onSegment1(collisionPoint)) {
            currentVelocity = Velocity.fromAngleAndSpeed(SLIGHTLY_CHANGE, speedUnit);
        }
        if (segments[4].onSegment1(collisionPoint)) {
            currentVelocity = Velocity.fromAngleAndSpeed(SIGNIFICANT_CHANGE, speedUnit);
        }
        return currentVelocity;
    }

    /**
     * Adds paddle to the game.
     *
     * @param g Game we add it to.
     */
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }
}
