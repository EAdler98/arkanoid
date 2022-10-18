/**
 * a class represeint a rectangle, with size and location.
 */
public class Rectangle {

    private Point upperLeft, upperRight, lowerLeft, lowerRight;
    private double width, height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * @param line
     * @return a (possibly empty) List of intersection points
     *         with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersections = new java.util.ArrayList<Point>();

        Point p = line.intersectionWith(getUpperHorizontal());
        if (p != null) {
            intersections.add(p);
        }
        p = line.intersectionWith(getLowerHorizontal());
        if (p != null) {
            intersections.add(p);
        }
        p = line.intersectionWith(getLeftVertical());
        if (p != null) {
            intersections.add(p);
        }
        p = line.intersectionWith(getRightVertical());
        if (p != null) {
            intersections.add(p);
        }
        return intersections;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return Return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * @return the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        return lowerLeft;
    }

    /**
     * @return the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return lowerRight;
    }

    /**
     * @return the top line of the rectangle.
     */
    public Line getUpperHorizontal() {
        return new Line(upperLeft, upperRight);
    }

    /**
     * @return the buttom line of the rectangle.
     */
    public Line getLowerHorizontal() {
        return new Line(lowerLeft, lowerRight);
    }

    /**
     * @return the top left of the rectangle.
     */
    public Line getLeftVertical() {
        return new Line(upperLeft, lowerLeft);
    }

    /**
     * @return the right line of the rectangle.
     */
    public Line getRightVertical() {
        return new Line(upperRight, lowerRight);
    }
}