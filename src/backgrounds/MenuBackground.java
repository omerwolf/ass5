package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

public class MenuBackground extends Background {
    private Color color;

    public MenuBackground(Color initColor) {
        this.color = initColor;
    }
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rect = new Rectangle(new Point(0, 0), d.getWidth(), d.getHeight());
        rect.setColor(Color.blue);
        rect.drawOn(d);
    }
}
