package backgrounds;

import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;

public abstract class Background implements Sprite {

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
