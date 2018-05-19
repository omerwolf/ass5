import biuoop.DrawSurface;

import java.awt.*;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private static long milliSecondsSlept;
    private int countdownReverse;
    private int guiWidth;
    private int guiLength;

    /**
     * A Countdown Animation constructor.
     * @param initNumOfSeconds1
     *            - How many seconds the countdown should take alltogether.
     * @param initCountFrom
     *            - First number to countdown from.
     * @param currGameScreen
     *            - All the sprites on the screen.
     */
    public CountdownAnimation(double initNumOfSeconds1, int initCountFrom, SpriteCollection currGameScreen) {
        this.numOfSeconds = initNumOfSeconds1;
        this.countFrom = initCountFrom;
        this.gameScreen = currGameScreen;
        this.countdownReverse = 0;
        this.guiLength = 600;
        this.guiWidth = 800;

    }
    /**
     * Runs one frame according to this spesific animation (Countdown).
     * @param d
     *            - Draw Surface
     */
    public void doOneFrame(DrawSurface d) {
        String coutdownS = (this.countFrom != 0 ? Integer.toString(this.countFrom) : "GO!");
        ///init backdround
        Rectangle backGround = new Rectangle(new Point(0, 0), guiWidth, guiLength);
        backGround.setColor(Color.blue);
        backGround.drawOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, coutdownS, 64);
        milliSecondsSlept += (1000 / 60);
        if (milliSecondsSlept > (this.numOfSeconds * 1000 / (this.countFrom + this.countdownReverse))) {
            milliSecondsSlept = 0;
            this.countdownReverse++;
            this.countFrom--;
        }
    }
    /**
     * Returns wheater the animation should stop
     * @return True if it's should stop. False otherwise.
     */
    public boolean shouldStop() {
        return (this.countFrom == -1);
    }

}