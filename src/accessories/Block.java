package accessories;

import backgrounds.Background;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import game.GameLevel;
import sprites.Sprite;
import listeners.HitListener;
import listeners.HitNotifier;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import collidables.GameEnvironment;
import collidables.CollisionInfo;
import collidables.Collidable;
import java.awt.Image;
import java.util.Map;


/**
 * A Block class.
 *
 * @author Omer Wolf.
 */


public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private int hitsCount;
    private List<HitListener> hitListeners;
    private Map<Integer, Background> background;
    private Color stroke;
    private Boolean isDeathRegion;
    /**
     * Create a new block by given a rectangle.
     * @param rec - rectangle - the shape and the location of the block.
     */
    public  Block(Rectangle rec) {
        this.shape = rec;
        this.hitsCount = 0;
        this.hitListeners = new ArrayList<HitListener>();
        this.isDeathRegion = false;
    }
    /**
     * Construct a block given a rectangle, number of hit points, background and a stroke.
     *
     * @param rect         is a rectangle.
     * @param hitPoints    is the number of hit points.
     * @param background   is the background of the block.
     * @param strokeColor  is the frame's rectangle.
     */
    public Block(Rectangle rect, int hitPoints, Map<Integer, Background> background, Color strokeColor) {
        this.shape = rect;
        this.hitsCount = hitPoints;
        this.hitListeners = new ArrayList<HitListener>();
        this.background = background;
        this.stroke = strokeColor;
    }

    /**
     * @return the block as a rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    /**
     * @param hitter - the ball who hit the block.
     * @param currentVelocity - the current velocity.
     * @param collisionPoint - the collision point.
     * @return the new velocity depend on the location of the collision point.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        this.decreaseHitsCount();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line upperLine = this.shape.getUpperLine();
        Line lowerLine = this.shape.getLowerLine();
        Line leftLine = this.shape.getLeftLine();
        Line rightLine = this.shape.getRightLine();
        if (upperLine.pointOnLine(collisionPoint, null) || lowerLine.pointOnLine(collisionPoint, null)) {
            Velocity retVel = new Velocity(dx, -dy);
            return retVel;
        }
        if (leftLine.pointOnLine(collisionPoint, null) || rightLine.pointOnLine(collisionPoint, null)) {
            Velocity retVel = new Velocity(-dx, dy);
            return retVel;
        }

        return currentVelocity;
    }
    /**
     * @param drawS the given DrawSurface to draw the ball on it
     */
    public void drawOn(DrawSurface drawS) {
        drawS.setColor(this.shape.getColor());
        drawS.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        drawS.setColor(Color.BLACK);
        drawS.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        int alignment = 5;    //Necessary for fixed font
        int midX = (int) (this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2 - alignment);
        int midY = (int) (this.shape.getUpperLeft().getY() + this.shape.getHeight() / 2 + alignment);
        String hitsNum;
        int sizeOfText = 15;
        if (this.hitsCount < 1) {
            hitsNum = "X";
        } else {
            hitsNum = String.valueOf(this.hitsCount);
        }
        drawS.setColor(Color.WHITE);
        drawS.drawText(midX, midY, hitsNum, sizeOfText);
    }

    /**
     * Decrease the hit number by 1.
     */
    public void decreaseHitsCount() {
        this.hitsCount--;
    }
    /**.
     * this method set the hit number of the block
     * @param hitsCount1 - the new hit number.
     */
    public void setHitsCount(int hitsCount1) {
        this.hitsCount = hitsCount1;
    }
    /**
     * @param g a game.
     * Remove the block to the sprite and collidable lists .
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
    /**.
     * this method get the hit number of the block
     * @return  hitsCount - the current hit number.
     */
    public int getHitsCount() {
        return this.hitsCount;
    }

    @Override
    public void addHitListener(HitListener hl) {

        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * Notifies all listeners that a hit occured.
     * @param hitter
     *            - The ball which hits this block.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * a sprite interface method.
     */
    @Override
    public void timePassed(double dt) {

    }
    /**
     * @param g a game. add the block to the sprite and collidable lists .
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**.
     * this method get death-region flag.
     * @return  isDeathRegion - the death-region flag.
     */
    public Boolean getDeathRegion() {
        return this.isDeathRegion;
    }
    /**.
     * this method set death-region flag.
     * @param  newDeathRegion - the new  death-region flag value.
     */
    public void setDeathRegion(Boolean newDeathRegion) {
        this.isDeathRegion = newDeathRegion;
    }

    public double getWidth() {
        return this.shape.getWidth();
    }
}
