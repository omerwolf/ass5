import biuoop.DrawSurface;
import java.awt.Color;

public class FinalFourBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Color randomColor = new Color(0, 102, 255);
        d.setColor(randomColor);
        d.fillRectangle(20, 20, d.getWidth(), d.getHeight());

        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(105 + i * 10, 400, 180 + i * 10, 600);
        }

        for (int i = 0; i < 10; i++) {
            d.drawLine(605 + i * 10, 120, 580 + i * 10, 600);
        }

        for (int i = 0; i < 10; i++) {
            d.drawLine(305 + i * 10, 250, 380 + i * 10, 600);
        }

        d.setColor(Color.GRAY);
        d.fillCircle(100, 400, 23);
        d.fillCircle(120, 420, 27);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(140, 390, 29);
        d.fillCircle(160, 420, 22);
        d.setColor(Color.GRAY.brighter().brighter());
        d.fillCircle(180, 400, 32);

        d.setColor(Color.GRAY);
        d.fillCircle(600, 100, 23);
        d.fillCircle(620, 140, 27);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(640, 110, 29);
        d.setColor(Color.GRAY.brighter().brighter());
        d.fillCircle(660, 130, 22);
        d.fillCircle(680, 120, 32);

        d.setColor(Color.GRAY);
        d.fillCircle(300, 250, 23);
        d.fillCircle(320, 290, 27);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(340, 260, 29);
        d.setColor(Color.GRAY.brighter().brighter());
        d.fillCircle(360, 280, 22);
        d.fillCircle(380, 270, 32);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
