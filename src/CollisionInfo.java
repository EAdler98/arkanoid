/**
 * class representing CollisionInfo.
 * including the point of collision and the obejct collided.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constrcut for collisionInfo.
     *
     * @param collisionPoint
     * @param collisionObject
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return the collisionObject at which the collision occurs.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}