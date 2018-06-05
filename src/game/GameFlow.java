package game;


import animation.Animation;
import animation.HighScoresScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


import highScore.HighScoresTable;
import highScore.ScoreInfo;
import indicators.Counter;
import levels.LevelInformation;
import animation.EndScreen;
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
    private HighScoresTable scoreTable;
    private File fileName;
    private GUI gui;


    /**
     * The constructor of the GameFlow.
     * @param ar - the animation runner
     * @param ks - the keyboard.
     * @param numOfLives - the initialize lives number
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int numOfLives, HighScoresTable highScoresTable, File file,
                     GUI gameGui) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.scoreCounter = new Counter();
        this.livesCounter = new Counter(numOfLives);
        this.scoreTable = highScoresTable;
        this.fileName = file;
        this.gui = gameGui;
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
        this.endGame();
    }

    /**
     * Running the end screen animation.
     */
    public void endGame() {
        if (this.livesCounter.getValue() == 0) {
            Animation loseScreen = new EndScreen(this.keyboardSensor, this.scoreCounter, "Game Over");
            Animation loseKSA = new KeyPressStoppableAnimation(this.keyboardSensor,"space", loseScreen);
            this.animationRunner.run(loseKSA);
            //EndScreen loseScreen = new EndScreen(this.keyboardSensor, this.scoreCounter, "Game Over.");
            //this.animationRunner.run(loseScreen);


        } else {
            Animation winScreen = new EndScreen(this.keyboardSensor, this.scoreCounter, "You Win!");
            Animation winKSA = new KeyPressStoppableAnimation(this.keyboardSensor,"space", winScreen);
            this.animationRunner.run(winKSA);
            //EndScreen winScreen = new EndScreen(this.keyboardSensor, this.scoreCounter, "You Win!");
            //this.animationRunner.run(winScreen);
        }

        int playerScore = this.scoreCounter.getValue();
        int playerScoreRank = this.scoreTable.getRank(playerScore);

        if (playerScoreRank <= this.scoreTable.size()) {
            // Run animation with score After all levels are done.
            //GUI gui = new GUI("",200,100);
            DialogManager dialog = this.gui.getDialogManager();
            String playerName = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");

            ScoreInfo scoreInfo = new ScoreInfo(playerName, playerScore);
            this.scoreTable.add(scoreInfo);
            try {
                this.scoreTable.save(this.fileName);
            } catch (IOException e) {
                System.err.println("Failed saving file");
                e.printStackTrace(System.err);
            }
        }
        Animation highScoresScreen = new HighScoresScreen(this.scoreTable);
        Animation highScoresKSA = new KeyPressStoppableAnimation(this.keyboardSensor,"space", highScoresScreen);
        this.animationRunner.run(highScoresKSA);
        //HighScoresScreen highScoresScreen = new HighScoresScreen(this.scoreTable);
        //this.animationRunner.run(highScoresScreen);
    }

}