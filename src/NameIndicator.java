import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The NameIndicator class.
 *
 * @author Omer Wolf
 */
public class NameIndicator implements Sprite {
    private String levelName;
    /**
     * The constructor of the NameIndicator.
     * @param initLevelName - the name of the level.
     */
    public NameIndicator(String initLevelName) {
        this.levelName = initLevelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int fontSize = 20;
        String result = "Level Name: " + this.levelName;
        d.setColor(Color.BLACK);
        d.drawText(500, 20, result, fontSize);
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
