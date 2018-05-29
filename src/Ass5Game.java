import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;
/**
 * A Ass5Game class.
 *
 * @author Omer Wolf.
 */
public class Ass5Game {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_LENGTH = 600;
    static final int INIT_NUM_OF_LIVES = 7;
    /**
     * the main program.
     * @param args from command line.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_LENGTH);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        } else {
            for (String arg: args) {
                if (arg.equals("1")) {
                    levels.add(new DirectHit());
                }
                if (arg.equals("2")) {
                    levels.add(new WideEasy());
                }
                if (arg.equals("3")) {
                    levels.add(new Green3());
                }
                if (arg.equals("4")) {
                    levels.add(new FinalFour());
                }
            }
        }
        GameFlow gameFlow = new GameFlow(ar, keyboard, INIT_NUM_OF_LIVES);
        gameFlow.runLevels(levels);
        gui.close();
    }
}
