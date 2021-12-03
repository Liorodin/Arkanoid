package Arkanoid;

import GameCore.Collidable;
import GameCore.CollisionInfo;
import Gui.geometry.Line;
import Gui.geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * creates a new environment for a game.
 */
public class GameEnvironment {

    // Fields
    private List<Collidable> collidables;

    // constructor

    /**
     * new list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * adds a new GameCore.Collidable to add to the list of collidables.
     *
     * @param c a new GameCore.Collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * removes a collidable object from the collidables list.
     *
     * @param c the object wanted to be removed
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * If this object will not collide with any of the collidables in this collection, return null. Else, return
     * the information about the closest collision that is going to occur.
     *
     * @param trajectory the line of the moviing object
     * @return the information about the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (collidables.isEmpty()) {
            return null;
        }
        //gets the data from the first collidable in the list of collidables
        Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidables.get(0).getCollisionRectangle());
        Collidable collisionObject = collidables.get(0);
        //check for the shortest collision
        for (int i = 1; i < collidables.size(); i++) {
            //gets new collision point with the collidable
            Point newCollisionPoint = trajectory.closestIntersectionToStartOfLine(collidables.get(i)
                    .getCollisionRectangle());
            //checks if the first collision wasn't null
            if (collisionPoint != null) {
                //checks who's closer
                if (newCollisionPoint != null && trajectory.start().distance(newCollisionPoint)
                        < trajectory.start().distance(collisionPoint)) {
                    collisionPoint = newCollisionPoint;
                    collisionObject = collidables.get(i);
                }
            } else {
                collisionPoint = newCollisionPoint;
                collisionObject = collidables.get(i);
            }
        }
        if (collisionPoint != null && collisionObject != null) {
            return new CollisionInfo(collisionPoint, collisionObject);
        }
        return null;
    }
}
