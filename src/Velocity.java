/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private double dx, dy;

    /**
     * Constructor for x,y coordinates.
     *
     * @param dx
     * @param dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * a static method to create velocity from angle and speed, whereas 0 degrees is
     * upwards.
     *
     * @param angle the angle of the velocity
     * @param speed the speed of the velocity
     * @return new velocity instace representing the calculated velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.toRadians(angle); // convert to radians (Math class uses Radians)
        dx = Math.sin(dx) * speed; // the horizontal diraction is sinus of the angle times the speed
        double dy = Math.toRadians(angle); // convert to radians (Math class uses Radians)
        dy = -Math.cos(dy) * speed; // the vertical diraction is cosinus of the angle times the speed
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx,y+dy).
     *
     * @param p the point to apply the velocity to.
     * @return a Point with the new location.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * get velocity speed.
     *
     * @return speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}