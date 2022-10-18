import biuoop.DrawSurface;

/** a class represinting a ball whith size,color and velocity. */
public class Ball implements Sprite {

    private Point point;
    private int size;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;

    /**
     * constructor for center point ,size and color and gameEnvironment.
     *
     * @param center
     * @param r
     * @param color
     * @param gameEnvironment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.point = center;
        size = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        v = new Velocity(1, 1);
    }

    /**
     * constructor for center point ,size and color.
     *
     * @param center
     * @param r
     * @param color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        size = r;
        this.color = color;
        v = new Velocity(1, 1); // default velocity
    }

    /**
     * remove ball from the game.
     *
     * @param game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * another constructor x,y, for center point ,size and color.
     *
     * @param x
     * @param y
     * @param r
     * @param color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        size = r;
        this.color = color;
        v = new Velocity(1, 1); // default velocity
    }

    // accessors
    /**
     * get the point of the ball.
     *
     * @return point
     */
    public Point getCenter() {
        return point;
    }

    /**
     * get the x coordinate.
     *
     * @return x
     */
    public int getX() {
        return (int) point.getX();
    }

    /**
     * get the y coordinate.
     *
     * @return y
     */
    public int getY() {
        return (int) point.getY();
    }

    /**
     * get size.
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * get color.
     *
     * @return size
     */
    public java.awt.Color getColor() {
        return color;
    }

    // accessors
    /**
     * get velocity.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return v;
    }

    // setters
    /**
     * a method to set the x coordiante.
     *
     * @param x
     */
    public void setX(int x) {
        Point temp = new Point(x, this.point.getY());
        point = temp;
    }

    /**
     * a method to set the y coordiante.
     *
     * @param y
     */
    public void setY(int y) {
        Point temp = new Point(this.point.getX(), y);
        point = temp;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) Math.round(point.getX()), (int) Math.round(point.getY()), size); // draw the circle
                                                                                                  // required
    }

    /**
     * a method to set the velocity.
     *
     * @param v the velocty to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * a method to set the gameEnvironment.
     *
     * @param gameEnvironment to set
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * a method to set the velocity using dx, dy coordinates.
     *
     * @param dx the horizontal coordiante
     * @param dy the vertical coordinate
     */
    public void setVelocity(double dx, double dy) {
        v = new Velocity(dx, dy);
    }

    /**
     * this method moves the ball one step in its velocity.
     */
    public void moveOneStep() {
        if (v.getDx() == 0 && v.getDy() == 0) {
            return;
        }
        double dx = v.getDx() / v.getSpeed() * size;
        double dy = v.getDy() / v.getSpeed() * size;
        Point start = new Point(this.getVelocity().applyToPoint(this.point).getX(),
                this.getVelocity().applyToPoint(this.point).getY());
        double x = start.getX(), y = start.getY();
        Point end = new Point(this.getVelocity().applyToPoint(this.point).getX() + dx,
                this.getVelocity().applyToPoint(this.point).getY() + dy);
        Line trajectory = new Line(start, end);
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        CollisionInfo tempCollision = collisionInfo;
        boolean horizontalHit = false, vreticalHit = false;
        CollisionInfo hCollision = null, vCollision = null;
        CollisionInfo collision = collisionInfo;
        if (collision != null) {
            Line upper = collision.collisionObject().getCollisionRectangle().getUpperHorizontal();
            Line lower = collision.collisionObject().getCollisionRectangle().getLowerHorizontal();
            Line right = collision.collisionObject().getCollisionRectangle().getRightVertical();
            Line left = collision.collisionObject().getCollisionRectangle().getLeftVertical();
            if (!vreticalHit && (upper.isPointInSegment(collision.collisionPoint())
                    || lower.isPointInSegment(collision.collisionPoint()))) {
                vreticalHit = true;
                vCollision = collision;
                if (tempCollision == null) {
                    tempCollision = collision;
                }
            }
            if (!horizontalHit && (right.isPointInSegment(collision.collisionPoint())
                    || left.isPointInSegment(collision.collisionPoint()))) {
                horizontalHit = true;
                hCollision = collision;
                if (tempCollision == null) {
                    tempCollision = collision;
                }
            }
        }
        if (vreticalHit || horizontalHit) {
            if (hCollision == vCollision) {
                v = hCollision.collisionObject().hit(this, hCollision.collisionPoint(), v);
            } else {
                if (hCollision != null) {
                    v = hCollision.collisionObject().hit(this, hCollision.collisionPoint(), v);
                }
                if (vCollision != null) {
                    v = vCollision.collisionObject().hit(this, vCollision.collisionPoint(), v);
                }
            }
            double distance = point.distance(tempCollision.collisionPoint()) - size;
            Velocity velocity = Velocity.fromAngleAndSpeed(90 + Math.toDegrees(Math.atan2(dy, dx)), distance - 0.1);
            this.point = velocity.applyToPoint(point);
            return;
        }
        this.point = new Point(x, y);

    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}