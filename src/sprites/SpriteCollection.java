//Elad Vizenblit
package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Class holds all the sprites collection in the game.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spritesList = new ArrayList<>();

    /**
     * adds a Sprite to the list.
     * @param s Sprite to be added
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.spritesList);
        for (int i = 0; i < spritesCopy.size(); i++) {
            spritesCopy.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.spritesList);
        for (int i = 0; i < spritesCopy.size(); i++) {
            spritesCopy.get(i).drawOn(d);
        }
    }

    /**
     * removes sprite from the game.
     * @param s sprite we want to be removed.
     */
    public void removeSprite(Sprite s) {
        spritesList.remove(s);
    }
}