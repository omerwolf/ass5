import java.util.List;

// a BallRemover is in charge of removing balls from the game, as well as keeping count
// of the number of balls that remain.
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    public BallRemover(Game newGame, Counter newRemovedBalls) {
        this.game = newGame;
        this.remainingBalls = newRemovedBalls;
    }
    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getDeathRegion() == true) {

            hitter.removeHitListener(this);
            hitter.removeFromGame(game);

            if (this.game.getRemainingBalls().getValue() == 0) {
                this.game.getRemainingLives().decrease();
            }
            this.remainingBalls.decrease();
        }
    }
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    public void setRemainingBalls(Counter newRemainingBalls) {
        this.remainingBalls = newRemainingBalls;
    }
}