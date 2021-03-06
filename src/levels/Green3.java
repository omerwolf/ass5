package levels;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import accessories.Velocity;

import accessories.Block;

import sprites.Sprite;

import geometry.Point;

import geometry.Rectangle;

import backgrounds.Green3Background;

/**
 * The level3 - Green3 class implements LevelInformation.
 *
 * @author Omer Wolf.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocity = new ArrayList<Velocity>();
        Velocity v1 = new Velocity(-5, -5);
        Velocity v2 = new Velocity(5, -5);
        listOfVelocity.add(v1);
        listOfVelocity.add(v2);
        return listOfVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 4 * 60;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Green3Background background = new Green3Background();
        return background;
    }

    @Override
    public List<Block> blocks() {

        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.gray);
        colors.add(Color.yellow);
        colors.add(Color.red);
        colors.add(Color.blue);
        colors.add(Color.white);

        List<Block> listOfBlock = new ArrayList<Block>();
        int height = 30;
        int width = 55;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Point upP = new Point(725 - width * j, 200 + height * i);
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
        return 40;
    }
}
