package GameCore;

import Arkanoid.Ball;
import Gui.geometry.Point;
import Gui.geometry.Rectangle;

/**
 * @author Leonardo Rodin 207377151
 * GameCore.Collidable interface
 */
public interface Collidable {
    /**
     * @return "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          the Arkanoid.Ball that's doing the hitting.
     * @param collisionPoint  point of collision
     * @param currentVelocity the current velocity of the object
     * @return The return is the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
