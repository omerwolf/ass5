package game;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import biuoop.KeyboardSensor;



import animation.Animation;
import animation.CountdownAnimation;
import animation.PauseScreen;
import sprites.SpriteCollection;
import accessories.Velocity;
import accessories.Ball;
import accessories.Block;
import accessories.Paddle;
import game.GameLevel;
import sprites.Sprite;
import listeners.HitListener;
import listeners.HitNotifier;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import listeners.PrintingHitListener;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import collidables.GameEnvironment;
import collidables.CollisionInfo;
import collidables.Collidable;
import indicators.Counter;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import indicators.NameIndicator;
import levels.LevelInformation;
import animation.EndScreen;
/**
 * A game class.
 *
 * @author Omer Wolf.
 */
public class GameLevel implements Animation {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_LENGTH = 600;
    private SpriteCollection sprites;
    private GameEnvironment gameE;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreCounter;
    private Counter remainingLives;
    private AnimationRunner runner;
    private boolean isRunning;
    private boolean isNewLevel;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInfo;
    /**
     * The constructor of the gameLevel.
     * @param levelInformation - the level info.
     * @param ks -  the keyboard.
     * @param ar the animation runner.
     * @param scoreCounter the score.
     * @param livesCounter the lives.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar,
                     Counter scoreCounter, Counter livesCounter) {
        this.sprites = new SpriteCollection();
        this.gameE = new GameEnvironment();
        this.remainingLives = livesCounter;
        this.scoreCounter = scoreCounter;
        this.runner = ar;
        this.keyboardSensor = ks;
        this.levelInfo = levelInformation;
        this.remainingBalls = new Counter();
        this.remainingBlocks = new Counter();
    }
    /**
     * Initialize the blocks of the game's Frame.
     * add it to the game.
     */
    public void initBorders() {
        int borderSize = 25;
        Rectangle blockRightR =
                new Rectangle(new Point(SCREEN_WIDTH - borderSize, 0), borderSize, SCREEN_LENGTH, Color.GRAY);
        Block blockRight = new Block(blockRightR);
        blockRight.addToGame(this);

        Rectangle blockLeftR = new Rectangle(new Point(0, 0), borderSize, SCREEN_LENGTH, Color.GRAY);
        Block blockLeft = new Block(blockLeftR);
        blockLeft.addToGame(this);

        Rectangle blockUpR = (new Rectangle(new Point(0, borderSize), SCREEN_WIDTH, borderSize, Color.GRAY));
        Block blockUp = new Block(blockUpR);
        blockUp.addToGame(this);
        ///create the white top border
        Rectangle blockUpRW = (new Rectangle(new Point(0, 0), SCREEN_WIDTH, borderSize, Color.WHITE));
        Block blockUpW = new Block(blockUpRW);
        blockUpW.addToGame(this);
        ///initialize death-region
        this.initDeathRegion(borderSize);
    }
    /**
     * Initialize the deathRegion of the game.
     * add it to the game.
     * @param borderSize - the lengh of the borders
     */
    public void initDeathRegion(int borderSize) {
        Rectangle deathRegionR =
                new Rectangle(new Point(0, SCREEN_LENGTH), SCREEN_WIDTH, borderSize, Color.GRAY);
        Block deathRegion = new Block(deathRegionR);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        deathRegion.setDeathRegion(true);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);
    }
    /**
     * Initialize the Paddle of every level by using the levelInformation.
     * add it to the game.
     */
    public void initPaddle() {
        int borderSize = 25;
        int paddleWidth = this.levelInfo.paddleWidth();
        int paddleLength = 20;
        Point initPoint = new Point(SCREEN_WIDTH / 2 - (paddleWidth / 2), SCREEN_LENGTH - borderSize);
        Rectangle shapeOfPaddle = new Rectangle(initPoint, paddleWidth, paddleLength, Color.orange);
        Paddle paddle = new Paddle(shapeOfPaddle, this.keyboardSensor, this.levelInfo.paddleSpeed(), SCREEN_WIDTH);
        paddle.addToGame(this);
    }
    /**
     * Initialize the Indicators of every level by using the levelInformation.
     * add it to the game.
     */
    public void initIndicator() {
         ///initialize score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        scoreIndicator.addToGame(this);

        ///initialize lives indicator
        LivesIndicator livesIndicator = new LivesIndicator(this.remainingLives);
        livesIndicator.addToGame(this);

        NameIndicator nameIndicator = new NameIndicator(this.levelInfo.levelName());
        nameIndicator.addToGame(this);
    }
    /**
     * Initialize the balls of every level by using the levelInformation.
     * add it to the game.
     */
    public void initBalls() {
        int ballRadius = 5;
        List<Velocity> listOfVelocity = this.levelInfo.initialBallVelocities();
        int listLen = listOfVelocity.size();
        Point initPoint = new Point(400, 550);
        for (int i = 0; i < listLen; i++) {
            Ball newBall = new Ball(initPoint, ballRadius, Color.WHITE);
            newBall.setVelocity(listOfVelocity.get(i));
            newBall.setBallGE(this.gameE);
            remainingBalls.increase();
            newBall.addToGame(this);
        }
    }
    /**
     * Initializes a paddle in the down middle of screen. Puts level's ball on
     * it.
     */
    public void createBallsOnTopOfPaddle() {
        // If a player died once at this level and It is not level initialization.
        if (!this.isNewLevel) {
            this.gameE.getCollList().remove(this.gameE.getCollList().size() - 1);
            this.sprites.getSpriteList().remove(this.sprites.getSpriteList().size() - 1);
        }
        this.initBalls();
        this.initPaddle();
    }
    /**
     * Initializes a background.
     * @param ds - DrawSurface
     */
    public void initBackground(DrawSurface ds) {
        Rectangle backGround = new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_LENGTH);
        backGround.setColor(Color.blue);
        backGround.drawOn(ds);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.sprites.addSprite(this.levelInfo.getBackground());
        this.initBorders();
        this.initIndicator();
        ///initialize block listener
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ///initialize score listener
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        ///initialize print hit listeners
        PrintingHitListener phl = new PrintingHitListener();
        ///initialize Blocks
        List<Block> blocksList = this.levelInfo.blocks();
        for (Block block : blocksList) {
            block.addHitListener(phl);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            this.remainingBlocks.increase();
            block.addToGame(this);
        }
        this.isNewLevel = true;
        }
      /**.
     * playOneTurn - create 1 animation loop
     */
    public void playOneTurn() {

        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.isRunning = true;
        this.runner.run(this);
    }
    /**.
     * doOneFrame - create 1 frame.
     * @param ds - DrawSurface.
     * @param dt - absolute time.
     */
      @Override
      public void doOneFrame(DrawSurface ds, double dt) {
          ///initialize background
          this.initBackground(ds);
          this.sprites.notifyAllTimePassed(dt);
          this.sprites.drawAllOn(ds);
          ///check the num remaining blocks or balls equal to zero
          if (this.remainingBlocks.getValue() == 0) {
              this.scoreCounter.increase(100);
              this.isRunning = false;
              return;
          }
          if (this.remainingBalls.getValue() == 0) {
              this.remainingLives.decrease();
              this.isNewLevel = false;
              this.isRunning = false;
              return;
          }
          if (this.keyboardSensor.isPressed("p") || this.keyboardSensor.isPressed("P")) {
              this.runner.run(new PauseScreen((this.keyboardSensor)));
          }
      }

    @Override
    public boolean shouldStop() {
        return !this.isRunning;
    }
    /**
     * Remove the given collidable to the environment.
     * @param c - a collidable.
     */
    public void removeCollidable(Collidable c) {
        gameE.removeCollidable(c);
    }
    /**
     * @param s - a sprite.
     * remove it to the sprite list.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
    /**
     * @return remainingBlocks counter.
     * get  remainingBlocks counter.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }
    /**
     * @return remainingBalls counter.
     * get  remainingBalls counter.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }
    /**
     * @return remainingLives counter.
     * get  remainingLives counter.
     */
    public Counter getRemainingLives() {
        return this.remainingLives;
    }
    /**
     * @param c - a collidable.
     * add a collidable to the list of game environment.
     */
    public void addCollidable(Collidable c) {
        if (this.gameE == null) {
            this.gameE = new GameEnvironment();
        }
        this.gameE.addCollidable(c);
    }
    /**
     * @param s - a sprite.
     * add a sprite to the list of game environment.
     */
    public void addSprite(Sprite s) {
        if (this.sprites == null) {
            this.sprites = new SpriteCollection();
        }
        this.sprites.addSprite(s);
    }
}
