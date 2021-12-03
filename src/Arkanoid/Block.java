package Arkanoid;

import GameCore.Collidable;
import GameCore.HitListener;
import GameCore.HitNotifier;
import GameCore.Sprite;
import Gui.geometry.Line;
import Gui.geometry.Point;
import Gui.geometry.Rectangle;
import GameCore.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * new GameCore.Collidable and GameCore.Sprite object Arkanoid.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // Fields
    private List<HitListener> hitListeners;
    private Rectangle rectangle;

    // constructor

    /**
     * @param rectangle the shape of the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        hitListeners = new ArrayList<>();
    }

    /**
     * @param rectangle the shape of the block
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.rectangle.setColor(color);
        hitListeners = new ArrayList<>();
    }

    /**
     * removes a block from the game.
     *
     * @param game the game in which the block will be removed
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //gets the sides of the block
        Line[] sides = rectangle.getRectangleSides();
        //if the object hits the top/bottom border of the rectangle, change the vertical direction of the velocity
        if (sides[Rectangle.UPPER_SIDE].isPointOnLine(collisionPoint)
                || sides[Rectangle.LOWER_SIDE].isPointOnLine(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //if the object hits the right/left border of the rectangle, change the horizontal direction of the velocity
        if (sides[Rectangle.LEFT_SIDE].isPointOnLine(collisionPoint)
                || sides[Rectangle.RIGHT_SIDE].isPointOnLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * draws a block on the DrawSurface.
     *
     * @param surface the surface which the block will be drown on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(rectangle.getColor());
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * notifies all listeners of a hitting event.
     *
     * @param hitter the Arkanoid.Ball that's doing the hitting.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
