import java.util.List;

/**
 * A Line class.
 *
 * @author Omer Wolf.
 */
public class Line {
    private Point p1;
    private Point p2;

    /**
     * constructor that create a line by given start and end points.
     *
     * @param start - the start point of the line.
     * @param end   - the end point of the line.
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }

    /**
     * constructor that create a line by given tow pairs of (x,y) coordinates.
     *
     * @param x1 - the x coordinate of the start point.
     * @param y1 - the y coordinate of the start point.
     * @param x2 - the x coordinate of the end point.
     * @param y2 - the y coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        double len = p1.distance(p2);
        return len;
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double xMid = (p1.getX() + p2.getX()) / 2;
        double yMid = (p1.getY() + p2.getY()) / 2;
        Point midP = new Point(xMid, yMid);
        return midP;
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return p1;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return p2;
    }

    /**
     * @param other - check if other and line current line intersect.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }

    /**
     * @param otherP - a point.
     * @param otherL - a line.
     * @return true if the intersection point is on the current and other lines,
     * false otherwise.
     */
    public boolean pointOnLine(Point otherP, Line otherL) {
        double p1X = p1.getX();
        double p1Y = p1.getY();
        double p2X = p2.getX();
        double p2Y = p2.getY();
        double otherPX = otherP.getX();
        double otherPY = otherP.getY();

        boolean check = false;
        if (otherL == null) {
            double minX = Math.min(p1X, p2X);
            double maxX = Math.max(p1X, p2X);
            double minY = Math.min(p1Y, p2Y);
            double maxY = Math.max(p1Y, p2Y);
            if ((otherPX >= minX - 0.0001) && (otherPX <= maxX + 0.0001)
                    && (otherPY >= minY - 0.0001) && (otherPY <= maxY + 0.0001)) {
                check = true;
            }
            return check;
        }
        if ((otherPX <= Math.max(p1X, p2X))
                && (otherPX >= Math.min(p1X, p2X))
                && (otherPY <= Math.max(p1Y, p2Y))
                && (otherPY >= Math.min(p1Y, p2Y))
                && (otherPX <= Math.max(otherL.start().getX(), otherL.end().getX()))
                && (otherPX >= Math.min(otherL.start().getX(), otherL.end().getX()))
                && (otherPY <= Math.max(otherL.start().getY(), otherL.end().getY()))
                && (otherPY >= Math.min(otherL.start().getY(), otherL.end().getY()))) {
            check = true;
        }
        return check;
    }

    /**
     * @param other - check if other and line current line intersect.
     * @return the intersection point if the lines intersect, and null
     * otherwise.
     **/
    public Point intersectionWith(Line other) {
        double a1 = p2.getY() - p1.getY();
        double b1 = p1.getX() - p2.getX();
        double c1 = a1 * p1.getX() + b1 * p1.getY();
        double a2 = other.end().getY() - other.start().getY();
        double b2 = other.start().getX() - other.end().getX();
        double c2 = a2 * other.start().getX() + b2 * other.start().getY();
        double det = (a1 * b2) - (a2 * b1);
        if (det == 0) {
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / det;
            double y = (a1 * c2 - a2 * c1) / det;
            Point intersectionP = new Point(x, y);
            if (pointOnLine(intersectionP, other)) {
                return intersectionP;
            }
        }
        return null;
    }

    /**
     * @param other - a line to check if the current line equals to.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if ((p1.equals(other.start()) && p2.equals(other.end())
                || (p1.equals(other.end()) && p2.equals(other.start())))) {
            return true;
        }
        return false;
    }

    /**
     * @param rect a rectangle
     * @return the closest intersection point to the start of the line between the line and the rectangle
     * if there is no intersection point, return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        /*
        List<Point> pointList = new ArrayList<Point>();
        pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        }
        Point closestPoint = pointList.get(0);
        double distance = this.start().distance(pointList.get(0));
        for (int i = 1; i < pointList.size(); i++) {
            if (this.start().distance(pointList.get(i)) < distance) {
                closestPoint = pointList.get(i);
            }
        }
        return closestPoint;
        */
        List<Point> listI = rect.intersectionPoints(this);
        if (listI.isEmpty()) {
            return null;
        }
        if (listI.size() == 1) {
            return listI.get(0);
        }
        if (this.start().distance(listI.get(0)) < this.start().distance(listI.get(1))) {
            return listI.get(0);
        }
        return listI.get(1);
        }

    }

