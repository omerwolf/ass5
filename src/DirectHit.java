import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
        return 5;
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
        Block redBlock = new Block(new Rectangle(new Point(385, 135), 30, 30, Color.RED));
        redBlock.setHitsCount(1);
        blocksList.add(redBlock);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
