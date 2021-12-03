package Levels;

import Arkanoid.Block;
import Arkanoid.GameLevel;
import Arkanoid.Paddle;
import Backgrounds.CityBackground;
import GameCore.LevelInformation;
import GameCore.Sprite;
import GameCore.Velocity;
import Gui.geometry.Rectangle;
import GameAnimation.Zone;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Leonardo Rodin 207377151
 * the 3rd level of the arkanoid game.
 */
public class Level3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new CityBackground();
    }

    @Override
    public List<Block> blocks() {
        int blockWidth = Zone.WIDTH / 16;
        int blockHeight = Zone.HEIGHT / 24;

        List<Block> blockList = new ArrayList<>();

        int blocksInRow = 12;
        int numOfColumns = 6;
        int startingY = GameLevel.BANNER_HEIGHT + blockHeight + 50;
        //creates blocks in a decreasing manner
        for (int i = 0; i < numOfColumns; i++, startingY += blockHeight, blocksInRow--) {
            // Finals
            int startingX = Zone.WIDTH - GameLevel.BORDER_DIM - blockWidth;
            Random rand = new Random();
            int red = rand.nextInt(GameLevel.COLOR_RANGE);
            int green = rand.nextInt(GameLevel.COLOR_RANGE);
            int blue = rand.nextInt(GameLevel.COLOR_RANGE);
            Color color = new Color(red, green, blue);
            for (int j = 0; j < blocksInRow; j++, startingX -= blockWidth) {
                blockList.add(new Block(new Rectangle(startingX, startingY, blockWidth, blockHeight), color));
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}
