import biuoop.DrawSurface;
/**
 * A Sprite interface.
 *
 * @author Omer Wolf.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d - the draw surface.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * add the sprite to the game.
     * @param g the game which the sprite added.
     */
    void addToGame(Game g);
}