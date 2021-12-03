package GameCore;

import Arkanoid.Block;

import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * GameCore.LevelInformation interface conttains all the specifics of a level in the game.
 */
public interface LevelInformation {
    /**
     * @return the number of balls int the level
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return a list of speeds of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * @return the height of the paddle
     */
    int paddleHeight();

    /**
     * @return the level name will be displayed at the top of the screen
     */
    String levelName();

    /**
     * @return Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
