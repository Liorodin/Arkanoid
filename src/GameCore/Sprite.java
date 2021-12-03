package GameCore;

import Arkanoid.GameLevel;
import biuoop.DrawSurface;

/**
 * @author Leonardo Rodin 207377151
 * game object that can be drawn to the screen.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d the drawing surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * adds a sprite to game.
     *
     * @param g the game
     */
    void addToGame(GameLevel g);
}
