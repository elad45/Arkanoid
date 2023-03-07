//Elad Vizenblit
package levels;

import biuoop.DrawSurface;
import gameConfig.GameLevel;
import interfaces.Sprite;

import java.awt.*;
/**
 * Level3 background screen.
 */
public class Level3Background implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.darkGray);
        //base of the building.
        d.fillRectangle(102, 450, 85, 150);
        d.setColor(Color.white);
        //windows
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                d.fillRectangle(110 + 15 * i, 460 + 20 * j, 10, 15);
            }
        }
        d.setColor(Color.lightGray);
        d.fillRectangle(130, 400, 30, 50);
        d.setColor(Color.GRAY);
        d.fillRectangle(138, 250, 15, 150);
        d.setColor(Color.YELLOW);
        d.fillCircle(145, 243, 10);
        d.setColor(Color.red);
        d.fillCircle(145, 243, 6);
        d.setColor(Color.white);
        d.fillCircle(145, 243, 3);
        d.fillCircle(142, 243, 1);
        d.fillCircle(145, 240, 1);
        d.fillCircle(145, 246, 1);
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
