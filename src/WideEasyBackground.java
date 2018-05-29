import biuoop.DrawSurface;
import java.awt.Color;
/**
 * A WideEasyBackground class.
 *
 * @author Omer Wolf
 */
public class WideEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue.darker().darker());
        d.fillRectangle(20, 20, d.getWidth(), d.getHeight());
        d.setColor(Color.PINK);

        for (int k = 0; k < 200; k++) {
            d.drawLine(200, 150, 20 + 4 * k, 300);

            d.setColor(java.awt.Color.RED);
            d.fillCircle(200, 150, 65);

            d.setColor(java.awt.Color.ORANGE);
            d.fillCircle(200, 150, 60);

            d.setColor(java.awt.Color.YELLOW);
            d.fillCircle(200, 150, 55);

        }
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
