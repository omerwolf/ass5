/**
 * A BallRemover class.
 *
 * @author Omer Wolf
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * The constructor of the Ball Remover .
     * @param newGame -  the game.
     * @param newRemovedBalls - the counter of the balls.
     */
    public BallRemover(GameLevel newGame, Counter newRemovedBalls) {
        this.game = newGame;
        this.remainingBalls = newRemovedBalls;
    }
    /**
     * this method remove from the game Balls that hit the deathRegion block.
     * decrease the number of balls.
     * @param beingHit the block
     * @param hitter the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getDeathRegion()) {

            hitter.removeHitListener(this);
            hitter.removeFromGame(game);

            if (this.game.getRemainingBalls().getValue() == 0) {
                this.game.getRemainingLives().decrease();
            }
            this.remainingBalls.decrease();
        }
    }
}