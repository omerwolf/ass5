import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;
import biuoop.KeyboardSensor;
/**
 * A game class.
 *
 * @author Omer Wolf.
 */
public class GameLevel implements Animation{
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_LENGTH = 600;
    static final int INIT_NUM_OF_LIVES = 2;
    static final int INIT_NUM_OF_BALLS = 4;

    private SpriteCollection sprites;
    private GameEnvironment gameE;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreTrakingCounter;
    private Counter remainingLives;
    private AnimationRunner runner;
    private boolean isRunning;
    private KeyboardSensor keyboardSensor;


    public GameLevel() {
        this.gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_LENGTH);
        this.sprites = new SpriteCollection();
        this.gameE = new GameEnvironment();
        Sleeper sleeper = new Sleeper();
        this.runner = new AnimationRunner(this.gui, 60, sleeper);
        this.keyboardSensor = this.gui.getKeyboardSensor();
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
    public void initBorders() {
        int borderSize = 25;
        Rectangle blockRightR =
                new Rectangle(new Point(SCREEN_WIDTH - borderSize, 0), borderSize, SCREEN_LENGTH, Color.GRAY);
        Block blockRight = new Block(blockRightR);

        Rectangle blockLeftR = new Rectangle(new Point(0, 0), borderSize, SCREEN_LENGTH, Color.GRAY);
        Block blockLeft = new Block(blockLeftR);

        Rectangle blockUpR = (new Rectangle(new Point(0, borderSize), SCREEN_WIDTH, borderSize, Color.GRAY));
        Block blockUp = new Block(blockUpR);
        ///create the white top border
        Rectangle blockUpRW = (new Rectangle(new Point(0, 0), SCREEN_WIDTH, borderSize, Color.WHITE));
        Block blockUpW = new Block(blockUpRW);
        ///initialize death-region
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        Block deathRegion = initDeathRegion(borderSize, ballRemover);

        Block[] borderBlocks = new Block[5];
        borderBlocks[0] = deathRegion;
        borderBlocks[1] = blockRight;
        borderBlocks[2] = blockLeft;
        borderBlocks[3] = blockUp;
        borderBlocks[4] = blockUpW;
        for (Block borders : borderBlocks) {
            borders.addToGame(this);
        }
    }
    public void initCounters() {
        ///initialize lives counter
        this.remainingLives = new Counter(INIT_NUM_OF_LIVES);
        ///initialize balls counter
        this.remainingBalls = new Counter();
        ///initialize score counter
        this.scoreTrakingCounter = new Counter();
        //initialize blocks counter
        this.remainingBlocks = new Counter();
    }
    public void initIndicator() {
         ///initialize score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreTrakingCounter);
        scoreIndicator.addToGame(this);

        ///initialize lives indicator
        LivesIndicator livesIndicator = new LivesIndicator(this.remainingLives);
        livesIndicator.addToGame(this);
    }

    public void initBalls() {
        Ball[] ballsArr = new Ball[INIT_NUM_OF_BALLS];
        int ballRadius = 5;
        ballsArr[0] = new Ball(new Point(400, 500), ballRadius, Color.WHITE);
        ballsArr[1] = new Ball(new Point(450, 400), ballRadius, Color.WHITE);
        ballsArr[2] = new Ball(new Point(500, 500), ballRadius, Color.WHITE);
        ballsArr[3] = new Ball(new Point(600, 400), ballRadius, Color.WHITE);
        //ballsArr[4] = new Ball(new Point(900, 500), ballRadius, Color.WHITE);
        //ballsArr[5] = new Ball(new Point(450, 400), ballRadius, Color.WHITE);
        //ballsArr[6] = new Ball(new Point(400, 500), ballRadius, Color.WHITE);
        //ballsArr[7] = new Ball(new Point(450, 400), ballRadius, Color.WHITE);
        for (Ball balls : ballsArr) {
            balls.setBallGE(gameE);
            balls.setVelocity(3, 3);
            remainingBalls.increase();
            balls.addToGame(this);
        }
    }
    public void createBallsOnTopOfPaddle() {
        if( this.remainingLives.getValue() != INIT_NUM_OF_LIVES) {
            this.gameE.getCollList().remove(this.gameE.getCollList().size() - 1);
            this.sprites.getSpriteList().remove(this.sprites.getSpriteList().size() - 1);
        }



        ///initialize Paddel
        int paddelWidth = 200;
        int paddelLength = 20;
        Point startLoc = new Point(SCREEN_WIDTH / 2 - paddelWidth / 2, SCREEN_LENGTH - paddelLength);
        Rectangle rec = new Rectangle(startLoc, paddelWidth, paddelLength);
        rec.setColor(Color.yellow);
        Paddle paddle = new Paddle(rec, this.keyboardSensor);
        paddle.addToGame(this);  //Sprites[0] & gameE[0] == paddle

        ///initialize Balls
        this.initBalls();

    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.initCounters();
        this.initBorders();
        this.initIndicator();
        ///initialize block listener
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ///initialize ball listener
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ///initialize score listener
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this, this.scoreTrakingCounter);
        ///initialize print hit listeners
        PrintingHitListener phl = new PrintingHitListener();
        ///initialize Blocks
        int blockWidth = 60;
        int blockLength = 30;
        int numOfRows = 7;
        int maxCol = 12;
        for (int i = 0; i < numOfRows; i++) {
            Color randomColor = this.generateRandomColor();
            for (int j = 0; j < maxCol - i; j++) {
                this.remainingBlocks.increase();
                Point upP = new Point(SCREEN_WIDTH - 100 - blockWidth * j, 100 + blockLength * i);
                Rectangle rBlock = new Rectangle(upP, blockWidth, blockLength, randomColor);
                Block newBlock = new Block(rBlock);

                newBlock.addToGame(this);
                if (i == 0) {
                    newBlock.setHitsCount(2);
                } else {
                    newBlock.setHitsCount(1);
                }
                newBlock.addHitListener(phl);
                newBlock.addHitListener(blockRemover);
                newBlock.addHitListener(scoreTrackingListener);
            }
        }






    }
      /**
     * playOneTurn - create 1 animation loop
     */
    public void playOneTurn() {

        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.isRunning = true;
        this.runner.run(this);

        /*
        Sleeper sleeper = new Sleeper();

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface drawS = gui.getDrawSurface();
            this.doOneFrame(drawS);


            gui.show(drawS);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            // Decrease the live counter of a player if all balls are lost.
            System.out.println("blocks = " + remainingBlocks.getValue());
            System.out.println("balls = " + remainingBalls.getValue());
            System.out.println("score = " + scoreTrakingCounter.getValue());
            System.out.println("lives = " + remainingLives.getValue());
        }
        */

    }
    void run() {
        while (this.remainingLives.getValue() != 0) {
            this.playOneTurn();
            this.remainingLives.decrease();
        }
        this.gui.close();
        return;
    }
      /**
     * Generate a Random Color.
     * @return random color
     */
    public Color generateRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        return  randomColor;
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
    public Block initDeathRegion(int borderSize, BallRemover ballRemover) {
        Rectangle deathRegionR =
                new Rectangle(new Point(0, SCREEN_LENGTH), SCREEN_WIDTH, borderSize, Color.GRAY);
        Block deathRegion = new Block(deathRegionR);
        deathRegion.setDeathRegion(true);
        deathRegion.addHitListener(ballRemover);
        return deathRegion;
    }

    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    public Counter getRemainingLives() {
        return this.remainingLives;
    }

    @Override
    public void doOneFrame(DrawSurface ds) {
        ///initialize background
        this.initBackground(ds);
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(ds);


        ///check the num remaining blocks or balls equal to zero
        if (this.remainingBlocks.getValue() == 0) {
            this.isRunning = false;
            return;
        }
        if (this.remainingBalls.getValue() == 0) {
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
    public void initBackground(DrawSurface ds) {
        Rectangle backGround = new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_LENGTH);
        backGround.setColor(Color.blue);
        backGround.drawOn(ds);
    }


}
