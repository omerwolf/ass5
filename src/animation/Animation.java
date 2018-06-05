package animation;

import biuoop.DrawSurface;
/**
 * A Animation interface.
 *
 * @author Omer Wolf
 */
public interface Animation {
    /**
     * do one frame of the game.
     * @param d - the surface.
     * @param dt - absolute time.
     */
    void doOneFrame(DrawSurface d, double dt);
    /**
     * check if the level should stop.
     * @return boolean if should stop.
     */
    boolean shouldStop();
}
