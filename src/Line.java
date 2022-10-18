/**
 * class represting a line with start point and end point.
 */
public class Line {

    private Point start, end;
    // constructors

    /**
     * a constructor for two points, representing the start and the end of the line.
     *
     * @param start
     * @param end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * another constructor for x,y coordinats os start and end points.
     *
     * @param x1 the x coordiante of start
     * @param y1 the y coordiante of start
     * @param x2 the x coordiante of end
     * @param y2 the y coordiante of end
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return line length
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return middle point
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return start point
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end point
     */
    public Point end() {
        return end;
    }

    /**
     * Returns true if lines are intersected or containd.
     *
     * @param other
     * @return true if lines intersected or containd.otherwise false
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) {
            return true;
        }

        if (isPointInSegment(other.start) || isPointInSegment(other.end)) {
            return true; // return true if lines does not cross each other, but one contains the other
        }
        return false;

    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other
     * @return intersction point if exists, otherwise null
     */
    public Point intersectionWith(Line other) {

        boolean thisVertical = start.getX() - end.getX() == 0;
        boolean otherVertical = other.start.getX() - other.end.getX() == 0;
        if (thisVertical && otherVertical) { // check if the two lines are vertical
            return null; // if the tow lines are vertical- they are not intersected
        }
        double m1 = 0, m2 = 0, b1 = 0, b2 = 0; // m1,m2 represnt the plane, b1,b2 represent the intercept
        if (!thisVertical) { // if this line is not vertical - get the equation (y = mx + b)
            m1 = (start.getY() - end.getY()) / (start.getX() - end.getX()); // m = (x2-x1)/(y2-y1)
            b1 = start.getY() - m1 * start.getX();
        }
        if (!otherVertical) { // if the other line is not vertical - get the equation (y = mx + b)

            m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX()); // m =
                                                                                                    // (x2-x1)/(y2-y1)
            b2 = other.start.getY() - m2 * other.start.getX();
        }
        /*
         * finding the x coordinate of the intersection. if one of the lines is vertical
         * - the intersection is in its x value. Otherwise calculate the intersection.
         */
        double x = 0;
        double y = 0;
        Point p;
        if (!thisVertical && !otherVertical) {
            if (m1 == m2) {
                return null; // if the lines are paralell - they don't have intersection point
            }
            x = (b2 - b1) / (m1 - m2); // if both lines are not vertical, find the intersection point using the
                                       // equation m1x + b1 = m2x + b2x
            y = m1 * x + b1; // find the y value
        }
        /*
         * if one of the lines is vertical the intersection happens in its x coordiane,
         * therefore set x to its x. the equation (y = mx + b) should be calculated
         * using the second line
         */
        if (thisVertical) {
            x = start.getX();
            y = m2 * x + b2;
        }
        if (otherVertical) {
            x = other.start.getX();
            y = m1 * x + b1;
        }
        /*
         * now we have the x and y coordinate of the interscetion of the endless lines.
         * now checking if the intersection of the finite segments themselves by
         * checking if
         * the point coordinate is on boths segments
         */
        p = new Point(x, y);
        if (isPointInSegment(p) && other.isPointInSegment(p)) {
            return p; // if the interscetion point is contained in both segments - return it.
        }
        return null; // the interscetion point is not contained in both segments - return null.

    }

    /**
     * this method is given a point on an endless line of this finite line,
     * and returns true if the point is on the finite line.
     *
     * @param p the point to check
     * @return true if the point is on the finite line, otherwise false.
     */
    public boolean isPointInSegment(Point p) {
        if (isXInSegment(p.getX()) && isYInSegment(p.getY())) {
            return true; // return true if both the x and y coordinates are on the finite line
        }
        return false;
    }

    /**
     * this method is given an x coordiante on an endless line of this finite line,
     * and returns true if the coordiante is on the finite line.
     *
     * @param x the coordiante to check
     * @return true if the coordiante is on the finite line, otherwise false.
     */
    public boolean isXInSegment(double x) {
        double min = Math.round(Math.min(start.getX(), end.getX()) * 100.0) / 100.0;
        double max = Math.round(Math.max(start.getX(), end.getX()) * 100.0) / 100.0;
        if (Math.round(x * 100.0) / 100.0 >= min && Math.round(x * 100.0) / 100.0 <= max) {
            return true;
        }
        return false;
    }

    /**
     * this method is given an y coordiante on an endless line of this finite line,
     * and returns true if the coordiante is on the finite line.
     *
     * @param y the coordiante to check
     * @return true if the coordiante is on the finite line, otherwise false.
     */
    public boolean isYInSegment(double y) {
        double min = Math.round(Math.min(start.getY(), end.getY()) * 100.0) / 100.0;
        double max = Math.round(Math.max(start.getY(), end.getY()) * 100.0) / 100.0;
        if (Math.round(y * 100.0) / 100.0 >= min && Math.round(y * 100.0) / 100.0 <= max) {
            return true;
        }
        return false;
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     *
     * @param other
     * @return true if lines are the same. otherwise false
     */
    public boolean equals(Line other) {
        return (start.equals(other.start) && end.equals(other.end)
                || end.equals(other.start) && start.equals(other.end));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect
     * @return closest Intersection ToStart Of Line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        double minDistance = start.distance(intersections.get(0));
        Point min = intersections.get(0);
        for (Point p : intersections) {
            if (start.distance(p) < minDistance) {
                minDistance = start.distance(p);
                min = p;
            }
        }
        return min;
    }

}
