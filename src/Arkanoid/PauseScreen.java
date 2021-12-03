package Arkanoid;

import GameAnimation.Animation;
import GameAnimation.Zone;
import biuoop.DrawSurface;

/**
 * @author Leonardo Rodin 207377151
 * Arkanoid.PauseScreen pauses the on-goiong animation.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(Zone.WIDTH / 5, d.getHeight() / 2, "paused -- press space to continue", Zone.WIDTH / 25);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
