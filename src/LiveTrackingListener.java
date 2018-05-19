public class LiveTrackingListener implements HitListener {
    private GameLevel game;
    private Counter currentLives;

    public LiveTrackingListener(GameLevel newGame, Counter livesCounter) {
        this.currentLives = livesCounter;
        this.game = newGame;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if(this.game.getRemainingBalls().getValue() == 0) {
            this.currentLives.decrease();
        }

    }
}
