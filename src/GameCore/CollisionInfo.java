package GameCore;

import Gui.geometry.Point;

/**
 * @author Leonardo Rodin 207377151
 * data of the collision
 */
public class CollisionInfo {

    // Fields
    private Point collisionPoint;
    private Collidable collisionObject;

    // constructor

    /**
     * creates a new GameCore.CollisionInfo.
     *
     * @param collisionPoint  point of collision
     * @param collisionObject object of collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}