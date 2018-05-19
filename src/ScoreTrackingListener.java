public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private Game game;

    public ScoreTrackingListener(Game newGame, Counter scoreCounter) {
        this.currentScore = scoreCounter;
        this.game = newGame;
    }
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        if(beingHit.getHitsCount() == 1) {
            this.currentScore.increase(10);
        }
        if(this.game.getRemainingBlocks().getValue() == 0) {
            this.currentScore.increase(100);
        }
    }
}