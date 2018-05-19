import biuoop.DrawSurface;

import java.awt.Color;

public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    public ScoreIndicator(Counter sc) {
        this.scoreCounter = sc;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int fontSize = 20;
        String result = "Score: "  + this.scoreCounter.getValue();
        d.setColor(Color.BLACK);
        d.drawText(300, 20, result, fontSize);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
