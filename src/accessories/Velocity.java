package accessories;


import geometry.Point;

/**
 * A Velocity class.
 *
 * @author Omer Wolf.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor of the ball's velocity by given the dx and dy coordinates.
     *
     * @param dx1 the x coordinate.
     * @param dy1 the y coordinate.
     */
    public Velocity(double dx1, double dy1) {
        this.dx = dx1;
        this.dy = dy1;
    }
    /**
     * @param p the point that we want to apply the new position on.
     * @return the new point with position (x+dx,y+dy).
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        Point newP = new Point(newX, newY);
        return newP;
    }
    /**
     * Setting the velocity according to the angle and speed.
     *
     * @param angle is the angle.
     * @param speed is the speed.
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle) - Math.PI / 2) * speed;
        double dy = Math.sin(Math.toRadians(angle) - Math.PI / 2) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * @return the dx of the velocity.
     */
    public double getDx() {
        return dx;
    }
    /**
     * @param  dx1 - new dx.
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }
    /**
     * @return the dy of the velocity.
     */
    public double getDy() {
        return dy;
    }
    /**
     * @param  dy1 - new dy.
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }
}