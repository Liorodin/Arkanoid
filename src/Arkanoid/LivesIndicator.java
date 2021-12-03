package Arkanoid;

import GameAnimation.Zone;
import GameCore.Counter;
import GameCore.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * Arkanoid.LivesIndicator stores the amount of lives left for the player
 */
public class LivesIndicator implements Sprite {

    //fields
    private Counter livesCounter;

    /**
     * constructor.
     *
     * @param lives number of lives the player has
     */
    public LivesIndicator(Counter lives) {
        livesCounter = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText((int) (Zone.WIDTH / 10), Zone.HEIGHT / 35, "Lives: " + livesCounter.getValue(),
                Zone.HEIGHT / 30);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
