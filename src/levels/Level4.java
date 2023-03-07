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
 * Level4 in the game.
 */
public class Level4 implements LevelInformation {

    static final int AMOUNT_OF_BALLS = 5;
    static final int PADDLE_SPEED = 7;
    static final int PADDLE_WIDTH = 200;
    private int numberOfBlocks;

    //first block
    private Point upperLeft = new Point(55, 135);
    private static final int ROWS = 7;
    private static final int COLUMNS = 15;
    private static final int WIDTH = 46;
    private static final int HEIGHT = 30;

    @Override
    public int numberOfBalls() {
        return AMOUNT_OF_BALLS;
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<Velocity>();
        for (int i = 0; i < AMOUNT_OF_BALLS; i++) {
            ballsVelocity.add(new Velocity(i, 3));
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

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Level4Background();
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<Block>();
        //Point upperLeft = FIRST_BLOCK_LOCATION; //point of first block to be set
        // backup of the first Block location so we can use to draw new lines based on previous line order.
        Point upperLeftRunner = this.upperLeft;
        //number of rows. this nested for is generating the blocks and saves them in both fields.
        for (int i = 0; i < ROWS; i++) {
            //just random numbers for setting colors
            Color color = new Color(80 + 20 * i, 30 + 25 * i, 120 + 20 * i);
            //number of columns
            for (int j = 0; j < COLUMNS; j++) {
                Block block = new Block(new Rectangle(upperLeft, WIDTH, HEIGHT), color);
                blocksList.add(block);
                //setting for the next block
                upperLeft = new Point(upperLeft.getX() + WIDTH, upperLeft.getY());
            }
            //    new settings for the next segment
            upperLeft = new Point(upperLeftRunner.getX(), upperLeftRunner.getY() + HEIGHT);
            upperLeftRunner = new Point(upperLeftRunner.getX(), upperLeftRunner.getY() + HEIGHT);

        }
        this.numberOfBlocks = blocksList.size();
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }

    @Override
    public Color screenColor() {
        return Color.cyan;
    }
}
