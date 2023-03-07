//Elad Vizenblit
package sprites;

import biuoop.DrawSurface;
import gameConfig.GameLevel;
import gameConfig.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rec;
    private Color color;
    /**
     * Constructor.
     * @param rec The rectnagle we want to have as a block.
     * @param color the Color of the block
     */
    public Block(Rectangle rec, Color color) {
        this.rec = rec;
        this.color = color;
        this.hitListeners = new LinkedList<HitListener>();
    }

    /**
     * returns the color.
     * @return Color of block
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Returns the rectangle.
     * @return Returns the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Changes the velocity direction of the ball based on the collision point with the block.
     * and also notifying the Block that it has been hit by changing isHit field to true.
     * @param hitter The ball that hits the block
     * @param collisionPoint The collision point
     * @param currentVelocity Current velocity
     * @return the new velocity of the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        Line leftVertical = new Line(rec.getUpperLeft(), rec.getBottomLeft());
        Line upperHorizontal = new Line(rec.getUpperLeft(), rec.getUpperRight());
        Line bottomHorizontal = new Line(rec.getBottomLeft(), rec.getBottomRight());
        Line rightVertical = new Line(rec.getUpperRight(), rec.getBottomRight());

        if (leftVertical.onSegment1(collisionPoint)) {
            currentVelocity = new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        }

        if (upperHorizontal.onSegment1(collisionPoint)) {
            currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));

        }

        if (bottomHorizontal.onSegment1(collisionPoint)) {
            currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        }

        if (rightVertical.onSegment1(collisionPoint)) {
            currentVelocity = new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * draws the blocks.
     * @param d surface we want to draw the blocks on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * nothing as of now.
     */
    public void timePassed() { }


    /**
     * adds block the game.
     * @param g game environment we want to add to
     */
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }

    /**
     * Removes the Block from the game.
     * @param game the game we are playing in.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }


    /**
     * Notify all of the registered HitListener objects by calling their hitEvent method.
     * @param hitter the hitting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
