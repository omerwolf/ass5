import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * The level 1 - DirectHit class implements LevelInformation.
 *
 * @author Omer Wolf
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVel = new ArrayList<Velocity>(1);
        ballVel.add(Velocity.fromAngleAndSpeed(180, 5));
        return ballVel;
    }

    @Override
    public int paddleSpeed() {
        return 3 * 60;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        DirectHitBackground background = new DirectHitBackground();
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point(385, 135), 30, 30, Color.cyan));
        block.setHitsCount(1);
        blocksList.add(block);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
