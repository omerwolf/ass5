/**
 * The BlockRemover class.
 *
 * @author Omer Wolf
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * the constructor of the BlockRemover.
     * @param newGame the game.
     * @param newRemovedBlocks the counter.
     */
    public BlockRemover(GameLevel newGame, Counter newRemovedBlocks) {
        this.game = newGame;
        this.remainingBlocks = newRemovedBlocks;
    }
    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the block.
     * @param hitter the hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitsCount() == 1) {
            this.remainingBlocks.decrease();
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
        }
    }
}