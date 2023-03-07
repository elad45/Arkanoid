//Elad Vizenblit
package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * This is the win screen class.
 */
public class WinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;
    private GUI gui;

    /**
     * Constructor.
     *
     * @param gui   the gui we're using.
     * @param score the score the game ended with.
     */
    public WinScreen(GUI gui, int score) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(200, d.getHeight() / 2, "You win! Your score is " + this.score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}