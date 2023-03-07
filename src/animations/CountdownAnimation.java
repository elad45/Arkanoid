//Elad Vizenblit
package animations;
/** The CountdownAnimation will display the given gameScreen,
 for numOfSeconds seconds, and on top of them it will show
 a countdown from countFrom back to 1, where each number will
 appear on the screen for (numOfSeconds / countFrom) seconds, before
 it is replaced with the next one.
*/

import biuoop.DrawSurface;
import interfaces.Animation;
import sprites.SpriteCollection;

import java.awt.*;

/**
 * makes a 3,2,1 counter before level starts.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private long startTime;
    private int counter;

    /**
     * Constructor.
     * @param numOfSeconds Amount of seconds the count takes.
     * @param countFrom Number to begin with the backwards count
     * @param gameScreen all the sprites we want to have on the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.counter = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis();

    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);

        // prints countdown number.
        d.setColor(Color.orange);
        d.drawText(400, 400, Integer.toString(counter), 50);

        //shows the following countdown when needed.
        if ((System.currentTimeMillis() - this.startTime) >= (this.numOfSeconds / (this.countFrom))) {
            this.startTime = System.currentTimeMillis();
            this.counter = this.counter - 1;
        }

        //when reaches 0, start the game.
        if (this.counter == 0) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}