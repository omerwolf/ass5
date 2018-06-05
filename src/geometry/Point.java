package geometry;

/**
 * Point class.
 *
 * @author Omer Wolf.
 */
public class Point {
    private double x;
    private double y;
    /**
     * Constructor of a point by given x and y coordinates.
     *
     * @param xc - the x coordinate.
     * @param yc - the y coordinate.
     */
    public Point(double xc, double yc) {
        this.x = xc;
        this.y = yc;
    }
    /**
     *
     * @param other - other point.
     * @return distance - distance between Point and other.
     */
    public double distance(Point other) {
        double otherX = other.getX();
        double otherY  = other.getY();
        double dis = Math.sqrt(((x - otherX) * (x - otherX)) + ((y - otherY) * (y - otherY)));
        return dis;
    }
    /**
     *
     * @param other - other point to check equality with Point.
     * @return true if the points are equal, and false if the points don't
     *         equal.
     */
    public boolean equals(Point other) {
        if (x != other.getX() || y != other.getY()) {
            return false;
        }
        return true;
    }
    /**
     * @return the x coordinate.
     */
    public double getX() {
        return x;
    }
    /**
     * @return the y coordinate
     */
    public double getY() {
        return y;
    }
    /**
     * @param x1 new x cordtination
     */
    public void setX(double x1) {
        this.x = x1;
    }
    /**
     * @param y1 new y cordtination
     */
    public void setY(double y1) {
        this.y = y1;
    }
}