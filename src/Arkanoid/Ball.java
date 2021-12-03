package Arkanoid;

import GameCore.Sprite;
import GameCore.CollisionInfo;
import Gui.geometry.Line;
import Gui.geometry.Point;
import GameCore.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author Leonardo Rodin 207377151
 * Arkanoid.Ball class
 */
public class Ball implements Sprite {

    // Finals
    private static final int COLOR_RANGE = 256;

    // Fields
    private int radius;
    private Point center;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    // constructor

    /**
     * @param center a Gui.geometry.Point that indicates the center of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        radius = r;
        this.color = color;
    }

    /**
     * @param x     x coordinate of the center of the ball
     * @param y     y coordinate of the center of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        center = new Point(x, y);
        radius = r;
        this.color = color;
    }

    /**
     * @param center a Gui.geometry.Point that indicates the center of the ball
     * @param r      the radius of the ball
     */
    public Ball(Point center, int r) {
        Random rand = new Random();
        this.center = center;
        radius = r;
        //generates a random color
        int red = rand.nextInt(COLOR_RANGE);
        int green = rand.nextInt(COLOR_RANGE);
        int blue = rand.nextInt(COLOR_RANGE);
        color = new Color(red, green, blue);
    }

    // accessors

    /**
     * @return the x coordinate of the center of the ball
     */
    public double getX() {
        return center.getX();
    }

    /**
     * @return the y coordinate of the center of the ball
     */
    public double getY() {
        return center.getY();
    }

    /**
     * @param environment the environment in which the ball moves
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * @return the radius of the ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * @return the color of the ball
     */
    public Color getColor() {
        return color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface which the ball will be drown on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) getX(), (int) getY(), radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) getX(), (int) getY(), radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * sets new velocity.
     *
     * @param dx the delta x of the ball
     * @param dy the delta y of the ball
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * moves the ball dx steps on the horizontal way and dy on the vertical
     * way, and at the same times keeping the ball from exiting the display.
     */
    public void moveOneStep() {
        if (velocity == null) {
            return;
        }
        Point newCenter = getVelocity().applyToPoint(center);
        Line trajectory = new Line(center, newCenter);
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        //checks if the ball had a collision on his trajectory
        if (collisionInfo == null) {
            center = newCenter;
        } else {
            center = trajectory.nearCollision(collisionInfo.collisionPoint(), velocity);
            //changes velocity according to where the ball hit
            setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity));
        }
    }

    /**
     * removes the ball from the game.
     *
     * @param game the game in which the ball will be removed
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
