package backgrounds;

import biuoop.DrawSurface;
import game.GameLevel;
import java.awt.Color;


import sprites.Sprite;

/**
 * A WideEasyBackground class.
 *
 * @author Omer Wolf
 */
public class WideEasyBackground extends Background {
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
}
