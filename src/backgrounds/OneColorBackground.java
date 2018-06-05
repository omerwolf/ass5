package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

public class OneColorBackground extends Background {
    private Color color;

    public OneColorBackground(Color newColor) {
        this.color = newColor;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rect = new Rectangle(new Point(0, 0), d.getWidth(), d.getHeight());
        rect.setColor(this.color);
        rect.drawOn(d);
    }
}
