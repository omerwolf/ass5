/**
 * The PrintingHitListener class.
 *
 * @author Omer Wolf
 */
public class PrintingHitListener implements HitListener {
    /**
     * @param beingHit the block.
     * @param hitter the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitsCount() + " points was hit.");
    }

}