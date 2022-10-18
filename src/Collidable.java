/**
 * Interface for Collidable object.
 * each Collidable object must have a rectagle representing the shape, and hit
 * method for when ot's hit by another object.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity.
     *
     * @return is the new velocity expected after the hit (based on
     *         the force the object inflicted on us).
     * @param hitter
     * @param collisionPoint
     * @param currentVelocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}