package Levels;

import Arkanoid.Block;
import Arkanoid.GameLevel;
import Arkanoid.Paddle;
import Backgrounds.SunBackground;
import GameAnimation.Zone;
import GameCore.LevelInformation;
import GameCore.Sprite;
import GameCore.Velocity;
import Gui.geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * the 2nd level of the arkanoid game.
 */
public class Level2 implements LevelInformation {

    // Finals
    public static final int NUM_OF_BLOCKS = 15;
    public static final int BLOCK_WIDTH = (int) ((Zone.WIDTH - 2 * GameLevel.BORDER_DIM) / NUM_OF_BLOCKS);

    @Override
    public int numberOfBalls() {
        return 10;
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
        return (int) (Zone.WIDTH * 2 / 3);
    }

    @Override
    public int paddleHeight() {
        return 25;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new SunBackground();
    }

    /**
     * @return returns a rainbow like list of colors
     */
    private List<Color> colors() {
        List<Color> colorList = new ArrayList<>();
        colorList.add(new Color(255, 0, 0));
        colorList.add(new Color(255, 0, 0));
        colorList.add(new Color(255, 128, 0));
        colorList.add(new Color(255, 128, 0));
        colorList.add(new Color(255, 255, 0));
        colorList.add(new Color(255, 255, 0));
        colorList.add(new Color(0, 255, 0));
        colorList.add(new Color(0, 255, 0));
        colorList.add(new Color(0, 255, 0));
        colorList.add(new Color(0, 0, 255));
        colorList.add(new Color(0, 0, 255));
        colorList.add(new Color(255, 204, 204));
        colorList.add(new Color(255, 204, 204));
        colorList.add(new Color(51, 255, 255));
        colorList.add(new Color(51, 255, 255));
        return colorList;
    }

    @Override
    public List<Block> blocks() {
        int blockHeight = Zone.HEIGHT / 24;
        //a specific startingX to center the row of blocks
        int startingX = (int) (GameLevel.BORDER_DIM + ((Zone.WIDTH - 2 * GameLevel.BORDER_DIM)
                - BLOCK_WIDTH * NUM_OF_BLOCKS) / 2);
        //the y coordinate of the blocks row
        int startingY = (int) (Zone.HEIGHT / 2.5);
        List<Color> colorList = colors();
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BLOCKS; i++, startingX += BLOCK_WIDTH) {
            blockList.add(new Block(new Rectangle(startingX, startingY, BLOCK_WIDTH, blockHeight), colorList.get(i)));
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }

}
