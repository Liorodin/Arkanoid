package Arkanoid;

import GameCore.Counter;
import GameCore.HitListener;

/**
 * @author Leonardo Rodin 207377151
 * Arkanoid.ScoreTrackingListener keeps the score of the current game
 */
public class ScoreTrackingListener implements HitListener {

    //fields
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter the current score of the game
     */
    public ScoreTrackingListener(Counter scoreCounter)  {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
