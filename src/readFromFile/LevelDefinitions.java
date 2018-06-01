package readFromFile;

import accessories.Block;
import accessories.Velocity;
import levels.LevelInformation;
import sprites.Sprite;

import java.io.File;
import java.util.List;

public class LevelDefinitions implements LevelInformation{
    private String levelName;
    private List<Velocity> ballVelocities;
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private File blockDefinitions ;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private int numBlocks;

    /**
     * A constructor.
     *
     * @param name        is the level's name to be set.
     * @param paddleSpeed is the speed of the paddle to be set.
     * @param paddleWidth is the width of the paddle to be set.
     * @param numBlocks   is the number of blocks to be set.
     * @param background  is the background's level to be set.
     */
    public Level(String name, int paddleSpeed, int paddleWidth, int numBlocks,
                 Sprite background) {
        this.levelName = name;
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;
        this.numBlocks = numBlocks;
        this.blocks = new ArrayList<Block>();
        this.background = background;


        @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 0;
    }

    @Override
    public String levelName() {
        return null;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }
}
