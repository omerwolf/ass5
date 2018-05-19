/**
 * A Collidable interface.
 *
 * @author Omer Wolf.
 */
public interface Collidable {
      /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @return the return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter - the ball who hit the block
     * @param currentVelocity the right velocity.
     * @param collisionPoint the collosion point.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}