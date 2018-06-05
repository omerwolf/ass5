package collidables;


import geometry.Point;
/**
 * A CollisionInfo class.
 *
 * @author Omer Wolf.
 */
public class CollisionInfo {
    private Collidable collObj;
    private Point collPoint;
    /**
     * @param collObj1 - one collidable object.
     * @param collPoint1 - a point.
     */
    public CollisionInfo(Collidable collObj1, Point collPoint1) {
        this.collObj = collObj1;
        this.collPoint = collPoint1;
    }
    /**
     * @return the point at where the collision occurs.
     */
    public Point collisionPoint() {
        return this.collPoint;
    }
    /**
     * @return the collidable object that involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collObj;
    }
}