package GameAnimation;

import biuoop.DrawSurface;

/**
 * @author Leonardo Rodin 207377151
 * GameAnimation.Animation interface
 */
public interface Animation {
    /**
     * runs specific tasks in one frame time.
     *
     * @param d the drawSurface which the game is running on
     */
    void doOneFrame(DrawSurface d);

    /**
     * knows when to stop.
     *
     * @return ture if it needs to stop, false otherwise
     */
    boolean shouldStop();
}
