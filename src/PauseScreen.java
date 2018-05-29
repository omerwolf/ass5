import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * A PauseScreen class implements Animation.
 *
 * @author Omer Wolf
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * Constructor  of a PauseScreen.
     *
     * @param k -  the keyboardSensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    @Override
    public boolean shouldStop() { return this.stop; }
}