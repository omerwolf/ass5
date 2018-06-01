package menu;

import animation.Animation;
/**
 * Menu interface.
 */
public interface Menu<T> extends Animation {
    void addSelection(String key, String message, T returnVal);
    T getStatus();
}