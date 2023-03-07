//Elad Vizenblit
package collidables;


import interfaces.Collidable;
import shapes.Point;

/**
 * This class holds details about the collision point.
 */
public class CollisionInfo {
    private final Point pointOfIntersection;
    private final Collidable collidedObject;

    /**
     * Constructor.
     * @param pointOfIntersection point of intersection.
     * @param collidedObject Shape.
     */
    public CollisionInfo(Point pointOfIntersection, Collidable collidedObject) {
        this.pointOfIntersection = pointOfIntersection;
        this.collidedObject = collidedObject;
    }

    /**
     * Get the collision Point.
     * @return Point collision point
     */
    public Point collisionPoint() {
        return this.pointOfIntersection;
    }

    /**
     * Get the collidable object involved in the collision.
     * @return Collidable collision object.
     */
    public Collidable collisionObject() {
        return this.collidedObject;
    }
}