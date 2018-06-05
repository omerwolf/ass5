package levels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import accessories.Velocity;

import accessories.Block;

import sprites.Sprite;

import geometry.Point;

import geometry.Rectangle;

import backgrounds.WideEasyBackground;
/**
 * The level 4 - WideEasy class implements LevelInformation.
 *
 * @author Omer Wolf.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int changeVel = -10;
        List<Velocity> listOfVelocity = new ArrayList<Velocity>();
        int numOfBalls = this.numberOfBalls();
        for (int i = 0; i < numOfBalls; i++) {
            Velocity v = new Velocity(changeVel, -4);
            changeVel = changeVel + 2;
            if (changeVel == 0) {
                changeVel = changeVel + 2;
            }
            listOfVelocity.add(v);
        }
        return listOfVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 2 * 60;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        WideEasyBackground background = new WideEasyBackground();
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlock = new ArrayList<Block>();
        Rectangle rBlock = new Rectangle(new Point(370, 300), 60, 30, java.awt.Color.BLACK);
        Block specialOne = new Block(rBlock);
        specialOne.setHitsCount(1);
        listOfBlock.add(specialOne);
        Random rand = new Random();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 7; i++) {
                //generate random color
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                java.awt.Color randomColor = new java.awt.Color(r, g, b);

                Rectangle rBlock1 = new Rectangle(new Point(20 + 50 * i + 410 * j, 300), 50, 30, randomColor);
                Block onlyOne = new Block(rBlock1);
                onlyOne.setHitsCount(1);
                listOfBlock.add(onlyOne);
            }
        }
        return listOfBlock;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
