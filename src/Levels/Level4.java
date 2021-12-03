package Levels;

import Arkanoid.Block;
import Arkanoid.GameLevel;
import Arkanoid.Paddle;
import Backgrounds.RainyBackground;
import GameAnimation.Zone;
import GameCore.LevelInformation;
import GameCore.Sprite;
import GameCore.Velocity;
import Gui.geometry.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level4 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return Velocity.getListVelocities(numberOfBalls());
    }

    @Override
    public int paddleSpeed() {
        return Paddle.MOVEMENT_SPEED;
    }

    @Override
    public int paddleWidth() {
        return (int) (Zone.WIDTH / 9);
    }

    @Override
    public int paddleHeight() {
        return 25;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new RainyBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int blockHeight = Zone.HEIGHT / 24;
        int blocksInRow = 15;
        int numOfColumns = 6;
        int startingY = (int) (Zone.HEIGHT / 8);
        for (int i = 0; i < numOfColumns; i++, startingY += blockHeight) {
            // Finals
            int startingX = (int) (GameLevel.BORDER_DIM + ((Zone.WIDTH - 2 * GameLevel.BORDER_DIM)
                    - Level2.BLOCK_WIDTH * Level2.NUM_OF_BLOCKS) / 2);
            Random rand = new Random();
            int red = rand.nextInt(GameLevel.COLOR_RANGE);
            int green = rand.nextInt(GameLevel.COLOR_RANGE);
            int blue = rand.nextInt(GameLevel.COLOR_RANGE);
            Color color = new Color(red, green, blue);
            for (int j = 0; j < blocksInRow; j++, startingX += Level2.BLOCK_WIDTH) {
                blockList.add(new Block(new Rectangle(startingX, startingY, Level2.BLOCK_WIDTH, blockHeight), color));
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
