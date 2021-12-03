package Levels;

import Arkanoid.Block;
import Arkanoid.GameLevel;
import Arkanoid.Paddle;
import Backgrounds.BullseyeBackground;
import GameCore.LevelInformation;
import GameCore.Sprite;
import GameCore.Velocity;
import Gui.geometry.Rectangle;
import GameAnimation.Zone;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * the 1st level of the arkanoid game.
 */
public class Level1 implements LevelInformation {

    //finals
    private static final int BLOCK_SIZE = Zone.WIDTH / 22;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(new Velocity(0, -(int) (Zone.HEIGHT / 120)));
        return velocityList;
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new BullseyeBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block(new Rectangle((int) ((Zone.WIDTH - BLOCK_SIZE) / 2),
                GameLevel.BANNER_HEIGHT * 7, BLOCK_SIZE, BLOCK_SIZE), Color.RED));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }


    /*public void drawFeatures(DrawSurface d) {
        Block background = new Block(new Rectangle(0, 0, Zone.WIDTH, Zone.HEIGHT), Color.BLACK);
        background.drawOn(d);

        d.setColor(Color.BLUE);
        //the length of the lines in the bullseye drawing
        int yValue = GameLevel.BANNER_HEIGHT * 7;
        double bullseyeLineLength = (new Line(Zone.WIDTH / 2, GameLevel.BANNER_HEIGHT + GameLevel.BORDER_DIM,
                Zone.WIDTH / 2, yValue - 5)).length();

        //draws upper line
        d.drawLine(Zone.WIDTH / 2, GameLevel.BANNER_HEIGHT, Zone.WIDTH / 2, yValue - 5);
        //draws left line
        d.drawLine(((Zone.WIDTH - BLOCK_SIZE) / 2 - 5), yValue + BLOCK_SIZE / 2,
                (int) ((Zone.WIDTH - BLOCK_SIZE) / 2 - 5 - bullseyeLineLength * 1.5), yValue + BLOCK_SIZE / 2);
        //draws right line
        d.drawLine(((Zone.WIDTH - BLOCK_SIZE) / 2 + 5 + BLOCK_SIZE), yValue + BLOCK_SIZE / 2,
                (int) ((Zone.WIDTH - BLOCK_SIZE) / 2 + 5 + BLOCK_SIZE + bullseyeLineLength * 1.5),
                yValue + BLOCK_SIZE / 2);
        //draws bottom line
        d.drawLine(Zone.WIDTH / 2, yValue + BLOCK_SIZE + 5, Zone.WIDTH / 2,
                (int) (yValue + BLOCK_SIZE + 5 + bullseyeLineLength * 1.5));

        //draws 3 rings across the center
        d.drawCircle(Zone.WIDTH / 2, (yValue + BLOCK_SIZE / 2), Zone.WIDTH / 15);
        d.drawCircle(Zone.WIDTH / 2, (yValue + BLOCK_SIZE / 2), Zone.WIDTH / 10);
        d.drawCircle(Zone.WIDTH / 2, (yValue + BLOCK_SIZE / 2), Zone.WIDTH / 7);
    }*/

}
