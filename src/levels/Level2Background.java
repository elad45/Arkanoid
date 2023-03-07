//Elad Vizenblit
package levels;

import biuoop.DrawSurface;
import gameConfig.GameLevel;
import interfaces.Sprite;

import java.awt.*;
/**
 * This is level2 background screen.
 */
public class Level2Background implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Color color = new Color(255, 255, 153);
        d.setColor(color);
        d.fillCircle(150, 120, 60);
        color = new Color(235, 235, 0);
        d.setColor(color);
        d.fillCircle(150, 120, 50);
        color = new Color(255, 235, 0);
        d.setColor(color);
        d.fillCircle(150, 120, 40);
        d.setColor(Color.YELLOW);
        for (int i = 0; i < 80; i++) {
            d.drawLine(150, 170, 60 + 8 * i, 290);
        }
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
