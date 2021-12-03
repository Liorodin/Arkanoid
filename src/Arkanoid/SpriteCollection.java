package Arkanoid;

import GameCore.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * controls the collection of all the sprites.
 */
public class SpriteCollection {

    // Fields
    private List<Sprite> sprites;

    // constructor

    /**
     * new list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * adds a new sprite to list of sprites.
     *
     * @param s the new sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removes a GameCore.Sprite object from the sprites list.
     *
     * @param s the sprite wanted to be remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> newSprites = new ArrayList<>(sprites);
        for (Sprite sprite : newSprites) {
            sprite.timePassed();
        }
    }

    /**
     * draws all the sprites on the DrawSurface.
     *
     * @param d the DrawSurface on which the sprites will be drown on
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> newSprites = new ArrayList<>(sprites);
        for (Sprite sprite : newSprites) {
            sprite.drawOn(d);
        }
    }
}
