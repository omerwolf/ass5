import java.util.List;

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    public BlockRemover(GameLevel newGame, Counter newRemovedBlocks) {
        this.game = newGame;
        this.remainingBlocks = newRemovedBlocks;
    }
    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitsCount() == 1) {
            this.remainingBlocks.decrease();
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
        }
    }
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    public void setRemainingBlocks(Counter newRemainingBlocks) {
        this.remainingBlocks = newRemainingBlocks;
    }
}