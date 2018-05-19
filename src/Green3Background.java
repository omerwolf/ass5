import biuoop.DrawSurface;
import java.awt.Color;

public class Green3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK.darker().darker());
        d.fillRectangle(20, 20, d.getWidth(), d.getHeight());

        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(50, 450, 120, 150);

        d.setColor(Color.GRAY);
        d.fillRectangle(85, 390, 50, 60);

        d.setColor(Color.GRAY.brighter());
        d.fillRectangle(105, 210, 10, 180);

        d.setColor(Color.white);
        d.fillCircle(700, 130, 60);

        d.setColor(Color.black);
        d.fillCircle(680, 120, 60);

        d.setColor(Color.red.darker());
        d.fillCircle(110, 200, 15);

        d.setColor(Color.white);
        d.fillCircle(110, 200, 10);

        d.setColor(Color.yellow);
        d.fillCircle(110, 200, 5);

        int width = 58;
        int height = 458;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.setColor(Color.white);
                d.fillRectangle(width + 23 * i, height + j * 30, 12, 20);

                d.setColor(Color.black);
                d.drawRectangle(width + 23 * i, height + j * 30, 12, 20);
            }
        }
        d.setColor(Color.white);
        d.fillCircle(400, 120, 2);
        d.fillCircle(100, 150, 2);
        d.fillCircle(150, 130, 2);
        d.fillCircle(250, 125, 2);
        d.fillCircle(280, 170, 2);
        d.fillCircle(450, 140, 2);
        d.fillCircle(560, 130, 2);
        d.fillCircle(500, 170, 2);
        d.fillCircle(620, 80, 2);
        d.fillCircle(650, 180, 2);
        d.fillCircle(720, 195, 2);
        d.fillCircle(50, 90, 2);
        d.fillCircle(750, 80, 2);
        d.fillCircle(300, 120, 2);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
