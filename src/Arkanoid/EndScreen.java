package Arkanoid;

import GameAnimation.Animation;
import GameAnimation.Zone;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * Arkanoid.EndScreen shows the resault screen
 */
public class EndScreen implements Animation {

    // Fields
    private boolean won;
    private int score;

    /**
     * constructor.
     *
     * @param won   if
     * @param score the final score of the game
     */
    public EndScreen(boolean won, int score) {
        this.score = score;
        this.won = won;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int size = (int) (Zone.WIDTH / 25);
        if (won) {
            d.setColor(Color.green);
            d.drawText(Zone.WIDTH / 5 - 2, d.getHeight() / 2 - 2, "You Win! Your score is " + score, size);
            d.setColor(Color.BLACK);
            d.drawText(Zone.WIDTH / 5, d.getHeight() / 2, "You Win! Your score is " + score, size);
        } else {
            d.setColor(Color.RED);
            d.drawText(Zone.WIDTH / 5 - 2, d.getHeight() / 2 - 2, "Game Over. Your score is " + score, size);
            d.setColor(Color.BLACK);
            d.drawText(Zone.WIDTH / 5, d.getHeight() / 2, "Game Over. Your score is " + score, size);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
