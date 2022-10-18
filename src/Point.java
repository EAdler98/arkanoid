/**
 * a class to represnt point with x,y coordiantes.
 */
public class Point {
    private double x, y;

    /**
     * constructor.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other
     * @return distance
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other
     * @return true if equals, otherwise false
     */
    public boolean equals(Point other) {
        if (Math.round(y * 100.0) / 100.0 == Math.round(other.getY() * 100.0) / 100.0
                && Math.round(x * 100.0) / 100.0 == Math.round(other.getX() * 100.0) / 100.0) {
            return true;
        }
        return false;
    }

    /**
     * Return the x and of this point.
     *
     * @return Return the x and y values of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Return the y and y values of this point.
     *
     * @return Return the y values of this point
     */
    public double getY() {
        return y;
    }
}