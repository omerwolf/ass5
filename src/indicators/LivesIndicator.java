package indicators;

import biuoop.DrawSurface;
import java.awt.Color;

import sprites.Sprite;



import game.GameLevel;
/**
 * The LiveIndicator class.
 *
 * @author Omer Wolf
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;
    /**
     * The constructor of the NLiveIndicator.
     * @param lc - the counter of the lives.
     */
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
    public void timePassed(double dt) {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
