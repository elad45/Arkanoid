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
 * Level3 of the game.
 */
public class Level3 implements LevelInformation {

    static final int AMOUNT_OF_BALLS = 5;
    static final int PADDLE_SPEED = 7;
    static final int PADDLE_WIDTH = 200;
    private int numberOfBlocks;
    private Point upperLeft = new Point(245, 185);
    private static final int WIDTH = 50;
    private static final int HEIGHT = 30;

    @Override
    public int numberOfBalls() {
        return AMOUNT_OF_BALLS;
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<Velocity>();
        for (int i = 0; i < AMOUNT_OF_BALLS; i++) {
            ballsVelocity.add(new Velocity(-2 + i, 3));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Sprite level3screen = new Level3Background();
        return level3screen;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<Block>();
        //Point upperLeft = FIRST_BLOCK_LOCATION; //point of first block to be set
        // backup of the first Block location so we can use to draw new lines based on previous line order.
        Point upperLeftRunner = this.upperLeft;
        //number of rows. this nested for is generating the blocks and saves them in both fields.
        int rows = 5;
        int columns = 10;
        for (int i = 0; i < rows; i++) {
            //just random numbers for setting colors
            Color color = new Color(50 + 20 * i, 40 + 20 * i, 120 + 20 * i);
            //number of columns
            for (int j = i; j < columns; j++) {
                Block block = new Block(new Rectangle(upperLeft, WIDTH, HEIGHT), color);
                blocksList.add(block);
                //setting for the next block
                upperLeft = new Point(upperLeft.getX() + WIDTH, upperLeft.getY());
            }
            //    new settings for the next segment
            upperLeft = new Point(upperLeftRunner.getX() + WIDTH, upperLeftRunner.getY() + HEIGHT);
            upperLeftRunner = new Point(upperLeftRunner.getX() + WIDTH, upperLeftRunner.getY() + HEIGHT);

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
        return Color.green;
    }
}
