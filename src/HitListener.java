/**
 * A HitListener interface.
 *
 * @author Omer Wolf
 */
public interface HitListener {
    /**
     *  This method is called whenever the beingHit object is hit.
     * @param beingHit - the block that being hit.
     * @param hitter - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}