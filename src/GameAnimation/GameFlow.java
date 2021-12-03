package GameAnimation;

import Arkanoid.EndScreen;
import Arkanoid.GameLevel;
import GameCore.Counter;
import GameCore.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * GameAnimation.GameFlow runs a bundle of levels
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    /**
     * constructor.
     *
     * @param ar the AnimationRunner
     * @param ks the KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        keyboardSensor = ks;
    }

    /**
     * plays a list of levels in a flow.
     *
     * @param levels the list of levels which will be played
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean won = true;
        Counter scoreCounter = new Counter();
        Counter lifeCounter = new Counter(7);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboardSensor, animationRunner, scoreCounter, lifeCounter);
            level.initialize();
            //runs until the player removed all the blocks or that he died
            while (level.getLifeCount() > 0 && level.getBlockCount() > 0) {
                level.run();
                level.addBallsToGame();
                level.movePaddleToMiddle();
            }
        }
        if (lifeCounter.getValue() == 0) {
            won = false;
        }
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                new EndScreen(won, scoreCounter.getValue())));
    }
}
