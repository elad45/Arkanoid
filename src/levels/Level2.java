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
 * This is level2 in the game.
 */
public class Level2 implements LevelInformation {

    static final int AMOUNT_OF_BALLS = 10;
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
            ballsVelocity.add(new Velocity(-5 + i, -3));
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Level2Background();
        return background;
    }

    @Override
    public Color screenColor() {
        return Color.red;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<Block>();
        Point upperLeft = new Point(55, 300);
        for (int i = 0; i < 2; i++) {
            blocksList.add(new Block(new Rectangle(upperLeft, 46, 30), Color.red));
            upperLeft = new Point(upperLeft.getX() + 46, upperLeft.getY());
        }
        for (int i = 2; i < 4; i++) {
            blocksList.add(new Block(new Rectangle(upperLeft, 46, 30), Color.orange));
            upperLeft = new Point(upperLeft.getX() + 46, upperLeft.getY());
        }
        for (int i = 4; i < 6; i++) {
            blocksList.add(new Block(new Rectangle(upperLeft, 46, 30), Color.YELLOW));
            upperLeft = new Point(upperLeft.getX() + 46, upperLeft.getY());
        }
        for (int i = 6; i < 9; i++) {
            blocksList.add(new Block(new Rectangle(upperLeft, 46, 30), Color.GREEN));
            upperLeft = new Point(upperLeft.getX() + 46, upperLeft.getY());
        }
        for (int i = 9; i < 11; i++) {
            blocksList.add(new Block(new Rectangle(upperLeft, 46, 30), Color.BLUE));
            upperLeft = new Point(upperLeft.getX() + 46,  upperLeft.getY());
        }
        for (int i = 11; i < 13; i++) {
            blocksList.add(new Block(new Rectangle(upperLeft, 46, 30), Color.PINK));
            upperLeft = new Point(upperLeft.getX() + 46, upperLeft.getY());
        }
        for (int i = 13; i < 15; i++) {
            blocksList.add(new Block(new Rectangle(upperLeft, 46, 30), Color.CYAN));
            upperLeft = new Point(upperLeft.getX() + 46, upperLeft.getY());
        }
        this.numberOfBlocks = blocksList.size();
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }
}
