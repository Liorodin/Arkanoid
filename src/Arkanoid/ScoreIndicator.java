package Arkanoid;

import GameCore.Counter;
import GameCore.Sprite;
import GameAnimation.Zone;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * Arkanoid.ScoreIndicator stores the score for the game
 */
public class ScoreIndicator implements Sprite {

    //fields
    private Counter scoreCounter;

    /**
     * constructor.
     *
     * @param score the score of the game
     */
    public ScoreIndicator(Counter score) {
        scoreCounter = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText((int) (Zone.WIDTH / 2.2), Zone.HEIGHT / 35, "Score: " + scoreCounter.getValue(), Zone.HEIGHT / 30);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
