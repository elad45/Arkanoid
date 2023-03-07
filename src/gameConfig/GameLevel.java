//Elad Vizenblit
package gameConfig;

import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import shapes.Point;
import shapes.Rectangle;
import sprites.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This class holds all the sprites and collidables we are having in the game.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter counterBlocks;
    private final Counter counterBalls;
    private Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation level;
    private final int numberOfBalls;
    private final List<Velocity> ballsVelocities;
    private final int paddleSpeed;
    private final int paddleWidth;
    private final String levelName;
    private final List<Block> initialBlocks;
    private final int blocksAmount;
    private final GUI gui;

    static final int SCREEN_BEGIN = 0;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int BLOCK_SPAN = 55;
    static final int SECONDS_COUNT_TAKES = 3;

    /**
     * Constructor.
     *
     * @param level    the level we want to play
     * @param keyboard keybaord we're using
     * @param runner   the animations we want to run on the screen
     * @param gui      the gui we're using
     * @param score    the current score.
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, GUI gui,
                     Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new ArrayList<Collidable>());
        this.counterBlocks = new Counter();
        this.counterBalls = new Counter();
        this.score = new Counter();
        this.gui = gui;
        this.level = level;
        this.keyboard = keyboard;
        this.runner = runner;
        this.numberOfBalls = level.numberOfBalls();
        this.ballsVelocities = level.initialBallVelocities();
        this.paddleSpeed = level.paddleSpeed();
        this.paddleWidth = level.paddleWidth();
        this.levelName = level.levelName();
        this.initialBlocks = level.blocks();
        this.blocksAmount = level.numberOfBlocksToRemove();
        this.score = score;
    }

    /**
     * Adds collidable to environment field.
     *
     * @param c adds the collidable we want to have in the game
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the SpriteCollection list.
     *
     * @param s Sprite we want to add to the game
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks,Ball and Paddle and add them to the game.
     */
    public void initialize() {
        //setting color the screen
        Block backScreen = new Block(new Rectangle(new Point(SCREEN_BEGIN, SCREEN_BEGIN),
                SCREEN_WIDTH, SCREEN_HEIGHT), level.screenColor());
        backScreen.addToGame(this);
        this.sprites.addSprite(level.getBackground());
        //creating game edges
        //upper block edge
        Block upperEdge = new Block(new Rectangle(new Point(SCREEN_BEGIN, SCREEN_BEGIN),
                SCREEN_WIDTH, BLOCK_SPAN), Color.gray);
        //right side edge
        Block rightEdge = new Block(new Rectangle(new Point(SCREEN_WIDTH - BLOCK_SPAN, BLOCK_SPAN), BLOCK_SPAN,
                SCREEN_HEIGHT - BLOCK_SPAN), Color.gray);
        // bottom edge
        Block bottomEdge = new Block(new Rectangle(new Point(SCREEN_BEGIN, SCREEN_HEIGHT - 1),
                SCREEN_WIDTH, BLOCK_SPAN), Color.gray);
        //left side edge
        Block leftEdge = new Block(new Rectangle(new Point(SCREEN_BEGIN, BLOCK_SPAN), BLOCK_SPAN,
                SCREEN_HEIGHT), Color.gray);
        //add them to the game.
        upperEdge.addToGame(this);
        rightEdge.addToGame(this);
        bottomEdge.addToGame(this);
        leftEdge.addToGame(this);

        //balls initialization
        Ball[] ball = new Ball[numberOfBalls];
        for (int i = 0; i < numberOfBalls; i++) {
            ball[i] = new Ball(new Point(415, 550), 3, Color.white, this.environment);
            ball[i].setVelocity(ballsVelocities.get(i));
            counterBalls.increase(1);
            ball[i].addToGame(this);
        }

        //blocks initialization
        for (int i = 0; i < blocksAmount; i++) {
            initialBlocks.get(i).addHitListener(new BlockRemover(this, counterBlocks));
            initialBlocks.get(i).addHitListener(new ScoreTrackingListener(score));
            initialBlocks.get(i).addToGame(this);
            counterBlocks.increase(1);
        }
        //adding listeners for removing balls when needed
        bottomEdge.addHitListener(new BallRemover(this, counterBalls));

        //creating Paddle
        Paddle paddle = new Paddle(upperEdge.getCollisionRectangle(), leftEdge.getCollisionRectangle(),
                bottomEdge.getCollisionRectangle(), rightEdge.getCollisionRectangle(), this.keyboard, this.paddleSpeed,
                this.paddleWidth);
        paddle.addToGame(this);
        this.running = true;

        //creating score display
        ScoreIndicator scoreDisplay = new ScoreIndicator(score);
        scoreDisplay.addToGame(this);

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.setColor(Color.black);
        d.drawText(500, 20, "Level Name: " + levelName, 20);
        if (this.keyboard.isPressed("p")) {
            PauseScreen pause = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", pause));
        }
        //level done successfully
        if (this.counterBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }

        //level failed
        if (this.counterBalls.getValue() == 0) {
            this.running = false;
        }

    }


    /**
     * This function makes the game movement.
     */
    public void run() {

        AnimationRunner animationRunner = new AnimationRunner(this.gui);
        animationRunner.run(new CountdownAnimation(SECONDS_COUNT_TAKES * 1000, 3,
                                                                       this.sprites));


        // use our runner to run the current level.
        this.runner.run(this);
    }


    /**
     * Remove a collidable.
     *
     * @param c the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a Sprite.
     *
     * @param s the sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Returns current amount of balls in the game.
     *
     * @return number of balls in the game
     */
    public int getCounterBalls() {
        return this.counterBalls.getValue();
    }
}