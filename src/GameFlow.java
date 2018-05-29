import biuoop.KeyboardSensor;
import java.util.List;
/**
 * The GameFlow class.
 *
 * @author Omer Wolf
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter scoreCounter;
    private Counter livesCounter;
    /**
     * The constructor of the GameFlow.
     * @param ar - the animation runner
     * @param ks - the keyboard.
     * @param numOfLives - the initialize lives number
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int numOfLives) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.scoreCounter = new Counter();
        this.livesCounter = new Counter(numOfLives);
    }
    /**
     * run the levels that are in the levels list.
     * after the levels, run the EndScreen animation.
     * @param levels the list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.scoreCounter, this.livesCounter);

            level.initialize();

            while (level.getRemainingBlocks().getValue() != 0 && level.getRemainingLives().getValue() != 0) {
                level.playOneTurn();
            }
        }
            if (this.livesCounter.getValue() == 0) {
                EndScreen loseScreen = new EndScreen(this.keyboardSensor, this.scoreCounter, "Game Over.");
                this.animationRunner.run(loseScreen);
            } else {
                EndScreen winScreen = new EndScreen(this.keyboardSensor, this.scoreCounter, "You Win!");
                this.animationRunner.run(winScreen);
            }
    }
}