//Elad Vizenblit
package levels;

import biuoop.DrawSurface;
import gameConfig.GameLevel;
import interfaces.Sprite;

import java.awt.*;
/**
 * This is level1 background screen.
 */
public class Level1Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.drawCircle(413, 177, 60);
        d.drawCircle(413, 177, 90);
        d.drawCircle(413, 177, 120);
        //vertical
        d.drawLine(413, 55, 413, 163);
        d.drawLine(413, 193, 413, 325);
        //horizontal
        d.drawLine(270, 177, 397, 177);
        d.drawLine(430, 177, 556, 177);
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
