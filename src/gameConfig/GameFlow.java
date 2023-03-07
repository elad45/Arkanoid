//Elad Vizenblit
package gameConfig;

import animations.AnimationRunner;
import animations.GameOver;
import animations.KeyPressStoppableAnimation;
import animations.WinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.LevelInformation;

import java.util.List;
/**
 * This class is connecting between the game levels. and displaying winner or game over when needed.
 */

public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private GUI gui;
    private Counter score;

    /**
     * Constructor.
     * @param ar the animations we want to run.
     * @param ks keyboard we're using.
     * @param gui the gui we're using.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.runner = ar;
        this.keyboard = ks;
        this.gui = gui;
        this.score = new Counter();
    }

    /**
     * this method runs the levels in correct order.
     * @param levels the levels we want to run
     */
    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.gui, score);
            level.initialize();

            while (!level.shouldStop()) {
                level.run();

            }
            //in case no more balls in the game, press space to close the game
            if (level.getCounterBalls() == 0) {
                Animation lose = new GameOver(this.gui, this.score.getValue());
                this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", lose));
                gui.close();
                }
        }
        //in case you win
        Animation win = new WinScreen(this.gui, this.score.getValue());
        this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", win));
        gui.close();
    }
}