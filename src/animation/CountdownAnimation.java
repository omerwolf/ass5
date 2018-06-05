package animation;

import biuoop.DrawSurface;
import java.awt.Color;

import biuoop.Sleeper;
import sprites.SpriteCollection;

/**
 * A CountdownAnimation class.
 * The CountdownAnimation will display the given gameScreen,
 *for numOfSeconds seconds, and on top of them it will show
 *a countdown from countFrom back to 1, where each number will
 *appear on the screen for (numOfSeconds / countFrom) seconds, before
 *it is replaced with the next one.
 *
 * @author Omer Wolf.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private static long milliSecondsSlept;
    private boolean stop;
    private boolean first;
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
        this.first = true;
        this.stop = false;
    }
    /**
     * Runs one frame according to this spesific animation (Countdown).
     * @param d
     *            - Draw Surface
     */
    public void doOneFrame(DrawSurface d, double dt) {

        this.gameScreen.drawAllOn(d);

        d.setColor(Color.WHITE);
        d.fillCircle(400, 285, 30);
        d.setColor(Color.black);
        d.drawCircle(400, 285, 30);
        d.setColor(Color.black);
        d.drawCircle(400, 285, 29);

        d.setColor(Color.RED);
        String ready = "Ready?";
        d.drawText(600, 100, ready, 50);


        d.setColor(Color.RED);
        String countDown = "!" + this.countFrom + "";
        d.drawText(382, 300, countDown, 50);

        if (this.first) {
            this.first = false;
        } else {
            Sleeper sleeper3 = new biuoop.Sleeper();
            sleeper3.sleepFor((long) 3 / (long) (this.numOfSeconds) * 1000);
        }
        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.countFrom--;

    }
    /**.
     * Returns wheater the animation should stop
     * @return True if it's should stop. False otherwise.
     */
    public boolean shouldStop() {
        return (this.countFrom == -1);
    }

}