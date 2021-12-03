package Arkanoid;

import GameAnimation.Animation;
import GameCore.LevelInformation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * The Arkanoid.CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    // Finals
    private final SpriteCollection gameScreen;
    private final long startTime;
    private final long milliSecondLeftToSleep;

    // Fields
    private LevelInformation levelInformation;
    private int countFrom;
    private boolean stop;
    private long relativeTime;

    /**
     * constructor.
     *
     * @param numOfSeconds     the waiting seconds between each number in the countdown
     * @param countFrom        starts countdown from it
     * @param gameScreen       all the sprites of the game
     * @param levelInformation all the information of the game
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              LevelInformation levelInformation) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.levelInformation = levelInformation;
        stop = false;
        startTime = System.currentTimeMillis();
        milliSecondLeftToSleep = (long) (1000 * numOfSeconds) / this.countFrom;
        relativeTime = milliSecondLeftToSleep;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 75);

        long passedTime = System.currentTimeMillis() - startTime;
        if (passedTime > relativeTime) {
            countFrom--;
            relativeTime += milliSecondLeftToSleep;
        }
        if (countFrom == 0) {
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
