//Elad Vizenblit
package listeners;

import gameConfig.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * This class is used for counting the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private final int scoreReward = 5; //destruction of a block rewards 5 points

    /**
     * Constructor.
     * @param scoreCounter current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * increasing the score by 5 points when block is hit.
     * @param beingHit Block that has been hit
     * @param hitter the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(scoreReward);
    }
}