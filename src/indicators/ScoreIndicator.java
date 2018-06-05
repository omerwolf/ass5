package indicators;

import biuoop.DrawSurface;
import java.awt.Color;
import sprites.Sprite;


import game.GameLevel;

/**
 * The ScoreIndicator class.
 *
 * @author Omer Wolf
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    /**
     * The constructor of the ScoreIndicator.
     * @param sc - the counter of the score.
     */
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
    public void timePassed(double dt) {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
