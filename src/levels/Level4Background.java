//Elad Vizenblit
package levels;

import biuoop.DrawSurface;
import gameConfig.GameLevel;
import interfaces.Sprite;

import java.awt.*;
/**
 * Level4 background screen.
 */
public class Level4Background implements Sprite {
    static final int SCREEN_BEGIN = 0;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    @Override
    public void drawOn(DrawSurface d) {

        for (int j = 0; j <= 400; j = j + 400) {
            for (int i = 0; i < 10; i++) {
                d.setColor(Color.white);
                d.drawLine(90 + j + 7 * i, 460 + j / 10, 60 + j + 7 * i, 597);
                d.setColor(Color.lightGray);
                //clouds.
                d.fillCircle(102 + j, 450 + j / 10, 20);
                Color color = new Color(220, 220, 220);
                d.setColor(color);
                d.fillCircle(122 + j, 470 + j / 10, 20);
                color = new Color(192, 192, 192);
                d.setColor(color);
                d.fillCircle(134 + j, 450 + j / 10, 20);
                color = new Color(211, 211, 211);
                d.fillCircle(140 + j, 470 + j / 10, 14);
                d.setColor(Color.lightGray);
                d.fillCircle(145 + j, 460 + j / 10, 10);
            }
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
