package menu;

import backgrounds.Background;
import backgrounds.MenuBackground;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.AnimationRunner;

import java.awt.Color;
import java.util.ArrayList;


public class MenuAnimation<T> implements Menu<T>{
    private String offSwitchKey;
    private String printMessage;
    private T status;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private boolean stop;
    private ArrayList<MenuOptionInfo<T>> options;


    public MenuAnimation(KeyboardSensor ks, AnimationRunner ar) {
        this.status = null;
        this.keyboardSensor = ks;
        this.runner = ar;
        this.stop = false;
        this.options = new ArrayList<MenuOptionInfo<T>>();

    }
    @Override
    public void addSelection(String key, String message,  T returnVal) {
        MenuOptionInfo<T> newOptionInfo = new MenuOptionInfo<>(key, message, returnVal);
        options.add(newOptionInfo);
    }
    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // initialize background
        MenuBackground background = new MenuBackground(Color.orange);
        background.drawOn(d);
        // draw menu text
        int offset = 0;
        for (MenuOptionInfo<T> option : this.options) {
            d.drawText(d.getWidth() / 20, (d.getHeight() / 3) + 50 * offset, "Press " + option.getKey() + " - ", 20);
            d.drawText(d.getWidth() / 5, (d.getHeight() / 3) + 50 * offset, option.getLineToPrint(), 20);
            offset++;
        }

        // wait for key press
        int indexCounte = 0;
        for (MenuOptionInfo<T> option : this.options) {
            indexCounte++;
            if (this.keyboardSensor.isPressed(option.getKey())) {
                indexCounte--;
                this.stop = true;
                this.status = this.options.get(indexCounte).getValue();
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    public void resetStop() { this.stop = false;}
}
