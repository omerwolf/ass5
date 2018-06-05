package levels;

import java.util.List;
import accessories.Velocity;
import accessories.Block;
import sprites.Sprite;

/**
 * A LevelInformation interface.
 *
 * @author Omer Wolf
 */
public interface LevelInformation {
    /**
     * num of blocks.
     * @return the num of blocks.
     */
    int numberOfBalls();
    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return list of balls.
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return the speed of the Paddle.
     */
    int paddleSpeed();
    /**
     * @return the width of the Paddle.
     */
    int paddleWidth();
    /**
     * @return the level name.
     * the level name will be displayed at the top of the screen.
     */
    String levelName();
    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();
    /**
     *  Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return num of blocks.
     */
    int numberOfBlocksToRemove();
}