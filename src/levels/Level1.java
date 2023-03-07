//Elad Vizenblit
package levels;

import gameConfig.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import shapes.Point;
import shapes.Rectangle;
import sprites.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Level1 of the game.
 */
public class Level1 implements LevelInformation {

    static final int AMOUNT_OF_BALLS = 1;
    static final int PADDLE_SPEED = 7;
    static final int PADDLE_WIDTH = 200;
    private int numberOfBlocks;

    @Override
    public int numberOfBalls() {
        return AMOUNT_OF_BALLS;
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<Velocity>();
        for (int i = 0; i < AMOUNT_OF_BALLS; i++) {
            ballsVelocity.add(new Velocity(0, -3));
        }
        return ballsVelocity;
    }
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;

    }

    /**
     * the level name will be displayed at the top of the screen.
     * @return level name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {

        Sprite background = new Level1Background();
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<Block>();
        Point upperLeft = new Point(398, 163);
        blocksList.add(new Block(new Rectangle(upperLeft, 30, 30), Color.red));
        this.numberOfBlocks = blocksList.size();
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
        //return blocks().size();
    }
    @Override
    public Color screenColor() {
        return Color.black;
    }
}
