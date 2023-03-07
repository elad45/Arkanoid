//Elad Vizenblit
package interfaces;

import gameConfig.Velocity;
import sprites.Block;

import java.awt.*;
import java.util.List;
/**
 * this interface includes the information we ant to have about a level.
 */
public interface LevelInformation {

    /**
     * Returns the number of balls a level have.
     *
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return list with the bal velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed.
     *
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * Paddle width.
     *
     * @return return paddle width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return level name
     */
    String levelName();

    /**
     * getter for the background screen.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * returns a list with all the blocks in the level.
     *
     * @return List of blocks
     */
    List<Block> blocks();

    /**
     * returns remaining blocks to remove.
     *
     * @return number of blocks in the level.
     */
    int numberOfBlocksToRemove();

    /**
     * The color we want to have as a background.
     *
     * @return Color of the screen we want to have
     */
    Color screenColor();
}