import java.util.LinkedList;
/**
 * The GameEnvironment class.
 *
 * @author Omer Wolf.
 */
public class GameEnvironment {
    private LinkedList<Collidable> collList;
    /**
     * construct a GameEnvironment.
     */
    public GameEnvironment() {
        this.collList = new LinkedList<Collidable>();
    }
    /**
     * add the given collidable to the environment.
     * @param c - a collidable.
     */
    public void addCollidable(Collidable c) {
        collList.add(c);
    }
    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - the trajectory of the ball.
     * @return the CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collList.isEmpty()) {
            return null;
        }
        //Find the first collision.
        int index = 1;
        Collidable coolObj = this.collList.get(0);
        Point collPoint = trajectory.closestIntersectionToStartOfLine(coolObj.getCollisionRectangle());
        while (collList.size() > index && collPoint == null) {
            coolObj = collList.get(index);
            collPoint = trajectory.closestIntersectionToStartOfLine(coolObj.getCollisionRectangle());
            index++;
        }
        //Case the last element in collList no intersect with the trajectory
        if (collPoint == null) {
            return null;
        }
        //Find the next collision and choose the closest one.
        double minDis = collPoint.distance(trajectory.start());
        double tempDis;
        Collidable tempCollObg;
        Point tempCollPoint;
        for (; index < collList.size(); index++) {
            tempCollObg = this.collList.get(index);
            tempCollPoint = trajectory.closestIntersectionToStartOfLine(tempCollObg.getCollisionRectangle());
            if (tempCollPoint == null) {
                continue;
            }
            tempDis = tempCollPoint.distance(trajectory.start());
            if (tempDis < minDis) {
                minDis = tempDis;
                coolObj = tempCollObg;
                collPoint = tempCollPoint;
                }
            }
            CollisionInfo coolInfo = new CollisionInfo(coolObj, collPoint);
            return coolInfo;
        }

    /**
     * Remove the given collidable to the environment.
     * @param c - a collidable.
     */
    public void removeCollidable(Collidable c) {
        collList.remove(c);
    }
    /**
     * @return the list of collidable.
     */
    public LinkedList<Collidable> getCollList() {
        return collList;
    }
}

