package Arkanoid;

import GameAnimation.Zone;
import GameCore.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * Arkanoid.NameIndicator stores the name of the game
 */
public class NameIndicator implements Sprite {

    //fields
    private String levelName;

    /**
     * constructor.
     *
     * @param name a given name
     */
    public NameIndicator(String name) {
        levelName = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText((int) (Zone.WIDTH / 1.5), Zone.HEIGHT / 35, "Level Name: " + levelName, Zone.HEIGHT / 30);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
