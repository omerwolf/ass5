import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * A AnimationRunner interface.
 *
 * @author Omer Wolf
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    /**
     * The constructor of the AnimationRunner.
     * @param initGui -  the gui.
     */
    public AnimationRunner(GUI initGui) {
        this.gui = initGui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }
    /**
     * run the animation of the game.
     * @param animation - the animation.
     */
    public void run(Animation animation) {
        double dt = (double) 1 / 60;
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, dt);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}