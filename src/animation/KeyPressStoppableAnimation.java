package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String keyName;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation newAnimation) {
        this.keyboardSensor = sensor;
        this.keyName = key;
        this.animation = newAnimation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);

        // Pressed for the first time.
        if (this.keyboardSensor.isPressed(this.keyName) && !this.isAlreadyPressed) {
            this.stop = true;
        }

        if (!this.keyboardSensor.isPressed(this.keyName)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}