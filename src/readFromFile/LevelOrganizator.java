package readFromFile;

import java.util.ArrayList;
import java.util.List;


import accessories.Block;
import accessories.Velocity;
import levels.LevelInformation;
import sprites.Sprite;

/**
 * LevelOrganizator class.
 */
public class LevelOrganizator implements LevelInformation {
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numBlocks;

    public LevelOrganizator(List<Velocity> initialBallVelocities, int paddleSpeed, int paddleWidth, String levelName,
                            Sprite background, int numBlocks) {
        this.initialBallVelocities = initialBallVelocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.blocks = new ArrayList<>();
        this.numBlocks = numBlocks;
    }

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return this.numBlocks;
    }

    public void setVelicities(List<Velocity> velocities) {
        this.initialBallVelocities = velocities;
    }

    public void setPaddleSpeed(int paddleSpd) {
        this.paddleSpeed = paddleSpd;
    }

    public void setPaddleWidth(int paddleWdt) {
        this.paddleWidth = paddleWdt;
    }

    public void setName(String name) {
        this.levelName = name;
    }

    public void setBackground(Sprite b) {
        this.background = b;
    }



    public void setNumOfBlocks(int numOfBlocks) {
        this.numBlocks = numOfBlocks;
    }
    /**
     * Adding block list to the level.
     *
     * @param blocksList is a block list.
     */
    public void addBlocks(List<Block> blocksList) {
        this.blocks.addAll(blocksList);
    }






}