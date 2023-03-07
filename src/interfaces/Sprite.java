//Elad Vizenblit
package interfaces;

import biuoop.DrawSurface;
import gameConfig.GameLevel;
/**
 * This interface contains all the objects in the game.
 */
public interface Sprite {
    /**
     * Draws the sprites to the screen.
     * @param d the surface we want to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Adds sprites to the game.
     * @param g the game we want to add the sprites to.
     */
    void addToGame(GameLevel g);
}