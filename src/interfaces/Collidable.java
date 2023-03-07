//Elad Vizenblit
package interfaces;


import gameConfig.GameLevel;
import gameConfig.Velocity;
import shapes.Point;
import shapes.Rectangle;
import sprites.Ball;
/**
 * This interface is for all the collidables in the game.
 */
public interface Collidable {
    /**
     * the shape of the object collided with.
     * @return the shape of the object we collided with
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object for collision.
     * @param hitter the hitting ball
     * @param collisionPoint the point the collision occurs
     * @param currentVelocity current velocity of the object
     * @return new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * adds the object to the game.
     * @param g the game we want to add the object to.
     */
    void addToGame(GameLevel g);
}