package Arkanoid;

import GameCore.Collidable;
import GameCore.Sprite;
import Gui.geometry.Rectangle;
import GameAnimation.Zone;
import GameCore.Velocity;
import Gui.geometry.Line;
import Gui.geometry.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Leonardo Rodin 207377151
 * paddle object and his properties in the game
 */
public class Paddle implements Sprite, Collidable {

    // Finals
    public static final int MOVEMENT_SPEED = Zone.WIDTH / 100;

    // Fields
    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;
    private Block paddleBlock;
    private int paddleSpeed;
    private GameLevel game;

    /**
     * creates a new paddle with a default speed.
     *
     * @param rectangle the shape of the paddle
     * @param keyboard  keyboard sensor
     */
    public Paddle(GameLevel game, Rectangle rectangle, biuoop.KeyboardSensor keyboard) {
        this.rectangle = rectangle;
        this.keyboard = keyboard;
        this.paddleBlock = new Block(rectangle);
        this.paddleSpeed = MOVEMENT_SPEED;
        this.game = game;
    }

    /**
     * creates a new paddle with a specific speed.
     *
     * @param rectangle   the shape of the paddle
     * @param keyboard    keyboard sensor
     * @param paddleSpeed the speed of the paddle
     * @param game        the game in which the paddle will be
     */
    public Paddle(GameLevel game, Rectangle rectangle, biuoop.KeyboardSensor keyboard, int paddleSpeed) {
        this.rectangle = rectangle;
        this.keyboard = keyboard;
        this.paddleBlock = new Block(rectangle);
        this.paddleSpeed = paddleSpeed;
        this.game = game;
    }

    /**
     * moves paddle left.
     */
    public void moveLeft() {
        rectangle.getUpperLeft().setX(rectangle.getUpperLeft().getX() - paddleSpeed);
    }

    /**
     * moves paddle right.
     */
    public void moveRight() {
        rectangle.getUpperLeft().setX(rectangle.getUpperLeft().getX() + paddleSpeed);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] sides = rectangle.getRectangleSides();
        double delta = rectangle.getWidth() / 5;
        double getUpperLeftX = rectangle.getUpperLeft().getX();
        //renders hits on the upper side of the block
        if (sides[Rectangle.UPPER_SIDE].isPointOnLine(collisionPoint)) {
            if (collisionPoint.getX() >= getUpperLeftX && collisionPoint.getX() < getUpperLeftX + delta) {
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() >= getUpperLeftX + delta && collisionPoint.getX() < getUpperLeftX + delta * 2) {
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() >= getUpperLeftX + delta * 2 && collisionPoint.getX()
                    < getUpperLeftX + delta * 3) {
                currentVelocity.setDy(-currentVelocity.getDy());
                return currentVelocity;
            }
            if (collisionPoint.getX() >= getUpperLeftX + delta * 3 && collisionPoint.getX()
                    < getUpperLeftX + delta * 4) {
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            }
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        hitter.removeFromGame(this.game);
        this.game.getBallCounter().decrease(1);
        return null;
    }

    @Override
    public void drawOn(DrawSurface d) {
        paddleBlock.drawOn(d);
    }

    @Override
    public void timePassed() {
        //the x position of the paddle
        double x = this.rectangle.getUpperLeft().getX();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            //if there's still distance between the paddle and the border blocks
            if (x - GameLevel.BORDER_DIM >= MOVEMENT_SPEED) {
                moveLeft();
            } else {
                rectangle.getUpperLeft().setX(GameLevel.BORDER_DIM);
            }
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            //if there's still distance between the paddle and the border blocks
            if (x + rectangle.getWidth() <= Zone.WIDTH - GameLevel.BORDER_DIM - MOVEMENT_SPEED) {
                moveRight();
            } else {
                rectangle.getUpperLeft().setX(Zone.WIDTH - GameLevel.BORDER_DIM - rectangle.getWidth());
            }
        }
    }

    /**
     * sets a new rectangle to paddle.
     *
     * @param newRectangle the new rectangle of the paddle
     */
    public void setNewRectangle(Rectangle newRectangle) {
        rectangle = newRectangle;
        paddleBlock = new Block(this.rectangle);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
