import biuoop.DrawSurface;
import java.awt.Color;
/**
 * A DirectHitBackground class.
 *
 * @author Omer Wolf
 */
public class DirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(20, 20, d.getWidth(), d.getHeight());

        String string = "Head Shot !";
        d.setColor(Color.green);
        d.drawText(60, 100, string, 35);

        d.setColor(java.awt.Color.WHITE);
        d.drawCircle(400, 150, 35);
        d.drawCircle(400, 150, 55);
        d.drawCircle(400, 150, 75);

        d.drawLine(315, 150, 485, 150);
        d.drawLine(400, 65, 400, 235);
    }

    @Override
    public void timePassed(double dt) {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
