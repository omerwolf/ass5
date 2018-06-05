package listeners;


import accessories.Ball;
import accessories.Block;
import indicators.Counter;

/**
 * A ScoreTrackingListener class implements HitListener.
 *
 * @author Omer Wolf
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * The constructor of the ScoreTrackingListener.
     * @param scoreCounter -  the counter of the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Blocks that are hit increase the scoreCounter by 5 points.
     * Blocks that are hit and removed from the game increase the scoreCounter by 10 points more.
     * @param beingHit the block.
     * @param hitter the hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        if (beingHit.getHitsCount() == 1) {
            this.currentScore.increase(10);
        }
    }
}