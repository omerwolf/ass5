import biuoop.DrawSurface;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    public ScoreIndicator(Counter sc) {
        this.scoreCounter = sc;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int fontSize = 20;
        String result = "Score: "  + this.scoreCounter.getValue();
        d.setColor(Color.red);
        d.drawText(300, 20, result, fontSize);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
