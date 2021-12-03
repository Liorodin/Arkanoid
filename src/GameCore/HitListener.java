package GameCore;

import Arkanoid.Ball;
import Arkanoid.Block;

/**
 * @author Leonardo Rodin 207377151
 * GameCore.HitListener interface
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the objects that is being hit
     * @param hitter   the Arkanoid.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
