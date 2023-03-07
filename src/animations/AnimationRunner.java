//Elad Vizenblit
package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * This class responsible for running the required frame animation.
 */

public class AnimationRunner {
    private GUI gui;
    private final int framesPerSecond;
    private Sleeper sleeper;
    private final int millisecond = 1000;
    /**
     * Constructor.
     * @param gui the gui we're using.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();

    }

    /**
     * Runs a frame animation.
     * @param animation the animation we want to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = this.millisecond / this.framesPerSecond;
        while (!animation.shouldStop()) {

            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
              this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }

}