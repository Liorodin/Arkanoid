package Arkanoid;

import GameCore.Counter;
import GameCore.HitListener;

/**
 * @author Leonardo Rodin 207377151
 * a Arkanoid.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    //fields
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game          the game that we are playing
     * @param removedBlocks the number of the remaining blocks in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}
