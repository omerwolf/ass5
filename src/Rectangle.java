/**
 * Rectangle class.
 *
 * @author Omer Wolf.
 */
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Rectangle class.
 *
 * @author Omer Wolf.
 */
class Rectangle {
    private Point location;                                  //The upper-left corner
    private double width;
    private double height;
    private Color color;
    /** constructor of a new rectangle with location and width/height.
     * @param upperLeft point
     * @param width1 width of the rectangle.
     * @param height1 height of the rectangle.
     * defualt color BLACK
     */
    public Rectangle(Point upperLeft, double width1, double height1) {
        this.location = upperLeft;
        this.width = width1;
        this.height = height1;
        this.color = Color.BLACK;
    }
    /** constructor of a new rectangle with location and width/height.
     * @param upperLeft point
     * @param width1 width of the rectangle.
     * @param height1 height of the rectangle.
     * @param newColor - color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width1, double height1, Color newColor) {
        this.location = upperLeft;
        this.width = width1;
        this.height = height1;
        this.color = newColor;
    }
    /**
     * @param line .
     * @return a (possibly empty) List of intersection points
     *  with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        double xp = location.getX();
        double yp = location.getY();
        Point pointUR = new Point(xp + width, yp);          //The upper-right corner
        Point pointDR = new Point(xp + width, yp + height); //The lower-right corner
        Point pointDL = new Point(xp, yp + height);         //The down-lower corner
        Line line1 = new Line(location, pointUR);           //The upper line
        Line line2 = new Line(pointUR, pointDR);            //The right line
        Line line3 = new Line(pointDR, pointDL);            //The lower line
        Line line4 = new Line(pointDL, location);           //The left line
        List<Line> listOfLines = new ArrayList<Line>();
        listOfLines.add(line1);
        listOfLines.add(line2);
        listOfLines.add(line3);
        listOfLines.add(line4);
        List<Point> listI = new ArrayList<Point>();
        for (int i = 0; i < 4; i++) {
            Point interPoint = line.intersectionWith(listOfLines.get(i));
            if (interPoint != null) {
                listI.add(interPoint);
            }
        }
        return listI;
    }
    /**
      * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }
     /**
      * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }
    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.location;
    }
    /**
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        double xp = location.getX();
        double yp = location.getY();
        Point pointUR = new Point(xp + width, yp);          //The upper-right corner
        return new Line(location, pointUR);
    }
    /**
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        double xp = location.getX();
        double yp = location.getY();
        Point pointUR = new Point(xp + width, yp);          //The upper-right corner
        Point pointDR = new Point(xp + width, yp + height); //The lower-right corner
        return new Line(pointUR, pointDR);
    }
    /**
     * @return the lower line of the rectangle.
     */
    public Line getLowerLine() {
        double xp = location.getX();
        double yp = location.getY();
        Point pointDR = new Point(xp + width, yp + height); //The lower-right corner
        Point pointDL = new Point(xp, yp + height);         //The down-lower corner
        return new Line(pointDR, pointDL);
    }
    /**
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        double xp = location.getX();
        double yp = location.getY();
        Point pointDL = new Point(xp, yp + height);        //The down-lower corner
        return new Line(pointDL, location);
    }
    /**
     * Set a new color to the rectangle.
     * @param newColor  - the new color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }
    /**
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return color;
    }
    /**
     * Set a new center to the rectangle.
     * @param location1  - the new location
     */
    public void setLocation(Point location1) {
        this.location = location1;
    }
    /**implements Sprite.
     * @param drawS the given DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface drawS) {
        drawS.setColor(this.color);
        drawS.fillRectangle((int) this.location.getX(), (int) this.location.getY(),
                (int) this.width, (int) this.height);
        drawS.setColor(Color.BLACK);
        drawS.drawRectangle((int) this.location.getX(), (int) this.location.getY(),
                (int) this.width, (int) this.height);
    }
}