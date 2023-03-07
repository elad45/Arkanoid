//Elad Vizenblit
package interfaces;


import sprites.Ball;
import sprites.Block;
/**
 * This interface is for the hit listeners we want to have, to notify them we a hit occurs.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit Block that has been hit
     * @param hitter the hitting ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
