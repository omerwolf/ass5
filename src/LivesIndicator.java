import biuoop.DrawSurface;

import java.awt.Color;

public class LivesIndicator implements Sprite {
    private Counter livesCounter;

    public LivesIndicator(Counter lc) {
        this.livesCounter = lc;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int fontSize = 20;
        String result = "lives: " + this.livesCounter.getValue();
        d.setColor(Color.BLACK);
        d.drawText(100, 20, result, fontSize);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
