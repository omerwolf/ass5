/**
 * A Ass3Game class.
 *
 * @author Omer Wolf.
 */
public class Ass3Game {
    /**
     * the main program.
     * @param args from command line.
     */
    public static void main(String[] args) {
        GameLevel game = new GameLevel();
        game.initialize();
        game.run();
    }
}
