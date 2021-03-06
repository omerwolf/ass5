package sprites;

import biuoop.DrawSurface;
import java.util.LinkedList;

/**
 * A SpriteCollection class.
 *
 * @author Omer Wolf.
 */
public class SpriteCollection {
    private LinkedList<Sprite> spriteList;
    /**
     * constructor of a new sprite collection
     * by create a new list.
     */
    public SpriteCollection() {
        this.spriteList = new LinkedList<Sprite>();
    }
    /**
     * @param s - a sprite.
     * add it to the sprite list.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }
    /**
     * call timePassed() on all sprites.
     * @param dt - absolute time.
     */
    public void notifyAllTimePassed(double dt) {
        LinkedList<Sprite> spriteCopy = new LinkedList<Sprite>(this.spriteList);
        for (Sprite sprite : spriteCopy) {
            sprite.timePassed(dt);
        }
    }
    /**
     * @param d - the draw surface.
     * call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
    /**
     * @param s - a sprite.
     * remove it to the sprite list.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
    /**
     * get it to the sprite list.
     * @return spriteList - LinkedList of sprites.
     */
    public LinkedList<Sprite> getSpriteList() {
        return spriteList;
    }
}