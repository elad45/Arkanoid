//Elad Vizenblit
package listeners;

import gameConfig.Counter;
import gameConfig.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;
/**
 * Removes falling balls.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Counstructor.
     * @param game game we play on
     * @param remainingBalls amount of blocks
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Removing blocks that are hit.
     * @param beingHit block to be removed
     * @param hitter ball that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}