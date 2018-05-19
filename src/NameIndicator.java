import biuoop.DrawSurface;

import java.awt.Color;

public class NameIndicator implements Sprite {
    private String levelname;

    public NameIndicator(String initLevelName) {
        this.levelname = initLevelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int fontSize = 20;
        String result = "Level Name: " + this.levelname;
        d.setColor(Color.BLACK);
        d.drawText(600, 20, result, fontSize);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
