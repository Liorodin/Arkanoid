package Arkanoid;

import GameCore.Counter;
import GameCore.HitListener;

/**
 * @author Leonardo Rodin 207377151
 * Arkanoid.BallRemover takes care of the removal of a ball from the game
 */
public class BallRemover implements HitListener {

    //fields
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game           the game that we are playing
     * @param remainingBalls the number of the remaining balls in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
