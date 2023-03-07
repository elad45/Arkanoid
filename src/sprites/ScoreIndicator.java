//Elad Vizenblit
package sprites;

import biuoop.DrawSurface;
import gameConfig.Counter;
import gameConfig.GameLevel;
import interfaces.Sprite;
import shapes.Point;
import shapes.Rectangle;

import java.awt.*;

/**
 * This class is showing the score board.
 */
public class ScoreIndicator implements Sprite {
    private Block scoreDisplay;
    private Counter score;

    static final int WIDTH = 800;
    static final int HEIGHT = 30;
    /**
     * Constructor.
     *
     * @param score score to display
     */
    public ScoreIndicator(Counter score) {
        this.scoreDisplay = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT), Color.white);
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        scoreDisplay.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(300, 20, "Score: " + this.score.getValue(), 20);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
        }
    }
}
