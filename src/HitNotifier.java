/**
 * A HitNotifer interface.
 *
 * @author Omer Wolf
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - the HitListener.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove the HitListener from the list of listeners to hit events.
     * @param hl - the HitListener.
     */
    void removeHitListener(HitListener hl);
}