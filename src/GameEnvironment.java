import java.util.ArrayList;

/** a calss for game enviroment, contains the objects. */
public class GameEnvironment {

    private java.util.List<Collidable> objects;

    /**
     * constructor for GameEnvironment.
     */
    public GameEnvironment() {
        objects = new ArrayList<Collidable>(); // initialize the list of collidables.
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c
     */
    public void addCollidable(Collidable c) {
        objects.add(c);
    }

    /**
     * remove the given collidable from the environment.
     *
     * @param c
     */
    public void removeCollidable(Collidable c) {
        objects.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory
     * @return nearest collisions
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        java.util.List<CollisionInfo> collisions = new java.util.ArrayList<CollisionInfo>();
        for (Collidable c : objects) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                collisions.add(new CollisionInfo(p, c));
            }
        }
        if (collisions.isEmpty()) {
            return null;
        }
        double minDistance = collisions.get(0).collisionPoint().distance(trajectory.start());
        CollisionInfo nearestCollisionInfo = collisions.get(0);
        for (CollisionInfo c : collisions) {
            if (c.collisionPoint().distance(trajectory.start()) < minDistance) {
                minDistance = c.collisionPoint().distance(trajectory.start());
                nearestCollisionInfo = c;
            }
        }
        return nearestCollisionInfo;

    }

}