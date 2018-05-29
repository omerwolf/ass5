import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A EndScreen class.
 *
 * @author Omer Wolf
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter scoreCounter;
    private String message;
    /**
     * A EndGameScreen Animation constructor.
     * @param ks
     *            - A KeyboardSensor.
     * @param endMessage
     *            - Win / Lose message.
     * @param score
     *            - Total score counter.
     */
    public EndScreen(KeyboardSensor ks, Counter score, String endMessage) {
        this.keyboard = ks;
        this.scoreCounter = score;
        this.message = endMessage + " Your score is ";
        this.stop = false;
    }
    /**
     * Runs one frame according to this spesific animation (End of Game screen).
     * @param d
     *            - Draw Surface
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, this.message + this.scoreCounter.getValue(), 50);
        d.drawText(10, d.getHeight() / 2 - 100, "Press space to quit", 25);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    /**.
     * Returns wheater the animation should stop
     * @return True if it's should stop. False otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
