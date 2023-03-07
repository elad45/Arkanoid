//Elad Vizenblit
package listeners;


import gameConfig.Counter;
import gameConfig.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
  */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game game we play on
     * @param removedBlocks amount of blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Removing the blocks that have been hit.
     * @param beingHit block to be removed
     * @param hitter ball that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}