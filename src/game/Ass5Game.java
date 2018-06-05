package game;

import animation.Animation;
import animation.HighScoresScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import accessories.Velocity;
import accessories.Ball;
import game.GameLevel;
import highScore.HighScoresTable;
import menu.Menu;
import menu.MenuAnimation;
import menu.MenuOptionInfo;
import menu.Task;
import sprites.Sprite;
import listeners.HitListener;
import listeners.HitNotifier;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import collidables.GameEnvironment;
import collidables.CollisionInfo;
import collidables.Collidable;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;
import levels.LevelInformation;
/**
 * A Ass5Game class.
 *
 * @author Omer Wolf.
 */
public class Ass5Game {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_LENGTH = 600;
    static final int INIT_NUM_OF_LIVES = 1;
    static final int HIGHSCORES_TABLE_SIZE = 10;
    static final String FILE_NAME = "highscores.ser";
    /**
     * the main program.
     * @param args from command line.
     */
    public static void main(String[] args) {
        try {
            final GUI gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_LENGTH);
            final AnimationRunner ar = new AnimationRunner(gui);
            final KeyboardSensor keyboard = gui.getKeyboardSensor();
            final File highScoreFile = new File(FILE_NAME);
            boolean stop = false;

            ///////// Trying to load a file table txt
            HighScoresTable tempScoresTable = new HighScoresTable(HIGHSCORES_TABLE_SIZE);
            if (highScoreFile.exists() && !highScoreFile.isDirectory()) {
                tempScoresTable.load(highScoreFile);
            } else {
                tempScoresTable.save(highScoreFile);
            }

            final HighScoresTable highScoresTable = tempScoresTable;
            ///////////

            List<LevelInformation> levels = new ArrayList<LevelInformation>();
            if (args.length == 0) {
                levels.add(new DirectHit());
                levels.add(new WideEasy());
                levels.add(new Green3());
                levels.add(new FinalFour());
            } else {
                for (String arg : args) {
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
            //GameFlow gameFlow = new GameFlow(ar, keyboard, INIT_NUM_OF_LIVES, highScoresTable, highScoreFile, gui);

            Task<Void> gameTask = new Task<Void>() {
                @Override
                public Void run() {
                    GameFlow gameFlow = new GameFlow(ar, keyboard, INIT_NUM_OF_LIVES, highScoresTable, highScoreFile, gui);
                    gameFlow.runLevels(levels);
                    return null;
                }
            };

            Task<Void> highScoreTask = new Task<Void>() {
                /**
                 *  Running the the animations.
                 * @return null.
                 */
                @Override
                public Void run() {
                    HighScoresScreen highScoresScreen = new HighScoresScreen(highScoresTable);
                    Animation highScoresKSA = new KeyPressStoppableAnimation(keyboard,"space", highScoresScreen);
                    ar.run(highScoresKSA);
                    return null;
                }
            };

            Task<Void> quitTask = new Task<Void>() {
                /**
                 *  Running the the animations.
                 * @return null.
                 */
                @Override
                public Void run() {
                    gui.close();
                    System.exit(0);
                    return null;
                }
            };

            MenuAnimation<Task<Void>> menuTask = new MenuAnimation<Task<Void>>(keyboard, ar);
            menuTask.addSelection("s", "Start Game", gameTask);
            menuTask.addSelection("h", "High Scores", highScoreTask);
            menuTask.addSelection("q", "Quit", quitTask);



            while(!stop) {
                ar.run(menuTask);
                menuTask.resetStop();
                Task<Void> task = menuTask.getStatus();
                task.run();
                try {
                    highScoresTable.save(highScoreFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ar.run(menuTask);
            }

            //gameFlow.runLevels(levels);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Trying to load a file table txt.
     *
     * @param file is the file.
     * @return the high scores table.
     * @throws Exception when failed to create file.
     */
    public HighScoresTable getHighScoresTable(File file) throws Exception {
        HighScoresTable scoresTable = new HighScoresTable(HIGHSCORES_TABLE_SIZE);
        if (file.exists() && !file.isDirectory()) {
            scoresTable.load(file);
        } else {
            scoresTable.save(file);
        }
        return scoresTable;
    }

}
