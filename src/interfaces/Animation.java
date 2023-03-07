//Elad Vizenblit
package interfaces;

import biuoop.DrawSurface;
/**
 * This interface is used for creating animations.
 */
public interface Animation {
    /**
     * Makes a single frame move.
     * @param d the surface we draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Determines if continue the animation or not.
     * @return True for stop False for continue
     */
    boolean shouldStop();
}