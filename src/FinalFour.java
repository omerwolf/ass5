import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
/**
 * The level4 - FinalFour class implements LevelInformation.
 *
 * @author Omer Wolf
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int changeVel = -6;
        List<Velocity> listOfVelocity = new ArrayList<Velocity>();
        int numOfBalls = this.numberOfBalls();
        for (int i = 0; i < numOfBalls; i++) {
            Velocity v = new Velocity(changeVel, -5);
            changeVel = changeVel + 3;
            if (changeVel == 0) {
                changeVel = changeVel + 3;
            }
            listOfVelocity.add(v);
        }
        return listOfVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 5 * 60;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        FinalFourBackground background = new FinalFourBackground();
        return background;
    }

    @Override
    public List<Block> blocks() {

        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.pink.brighter());
        colors.add(Color.pink);
        colors.add(Color.cyan);

        List<Block> listOfBlock = new ArrayList<Block>();
        int height = 30;
        int width = 51;
        for (int i = 0; i < 7; i++) {
            for (double j = 0; j < 15; j++) {
                Point upP = new Point(20 + width * j, 150 + 30 * i);
                Rectangle rBlock = new Rectangle(upP, width, height, colors.get(i));
                Block block = new Block(rBlock);
                block.setHitsCount(1);
                listOfBlock.add(block);
            }
        }
        return listOfBlock;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
