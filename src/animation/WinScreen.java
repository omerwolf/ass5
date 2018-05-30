package animation;

import biuoop.KeyboardSensor;
import indicators.Counter;

public class WinScreen extends EndScreen {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter scoreCounter;
    private String message;
    /**
     * A EndGameScreen Animation constructor.
     *
     * @param ks         - A KeyboardSensor.
     * @param score
     * @param endMessage - Win / Lose message.
     */
    public WinScreen(KeyboardSensor ks, Counter score, String endMessage) {
        super(ks, score, endMessage);
    }
}
