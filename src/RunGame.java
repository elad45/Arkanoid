//Elad Vizenblit

import animations.AnimationRunner;
import biuoop.GUI;
import gameConfig.GameFlow;
import interfaces.LevelInformation;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;

import java.util.ArrayList;
import java.util.List;

/**
 * This class runs the game.
 */
public class RunGame {

    /**
     * main function of the game, runs the game.
     *
     * @param args the order of levels.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("game", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);

        List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
        for (String s : args) {
            if (s.equals("1")) {
                levelsList.add(new Level1());
            }
            if (s.equals("2")) {
                levelsList.add(new Level2());
            }
            if (s.equals("3")) {
                levelsList.add(new Level3());
            }
            if (s.equals("4")) {
                levelsList.add(new Level4());
            }
        }
        if (levelsList.size() == 0) {
            levelsList.add(new Level1());
            levelsList.add(new Level2());
            levelsList.add(new Level3());
            levelsList.add(new Level4());
        }
        GameFlow game = new GameFlow(runner, keyboard, gui);
        game.runLevels(levelsList);
    }
}