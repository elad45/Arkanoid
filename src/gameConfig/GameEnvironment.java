//Elad Vizenblit
package gameConfig;

import collidables.CollisionInfo;
import interfaces.Collidable;
import shapes.Line;
import shapes.Point;

import java.util.ArrayList;
import java.util.List;
/**
 * A class which holds all the colliadbles items in the game.
 */
public class GameEnvironment {
    private List<Collidable> collidablesList;

    /**
     * Constructor.
     * @param collidablesList contains all the collidables in the game
     */
    public GameEnvironment(List<Collidable> collidablesList) {
        this.collidablesList =  collidablesList;
    }

    /**
     * Adds collidable to the game.
     * @param c collidable to be added.
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidablesList.add(c);
    }

    /**
     * This function returns the closest Collision item to the begining of trajectory.
     * @param trajectory the Line of the moving item.
     * @return CollisionInfo the closest collidable item to the start of trajectory.
     * returns null in case there's no collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //calls a function that saves all the collidables in the path of trajectory.
        //in case no such blocks returns null.
        List<CollisionInfo> blocksInPath = intersectTrajectory(trajectory);
        if (blocksInPath.size() == 0) {
            return null;
        }

        //saves the first point first as closest distance.
        double minDistance = trajectory.start().distance(blocksInPath.get(0).collisionPoint());
        CollisionInfo closestDistance = blocksInPath.get(0);
        //runs all over the list to search for the closest distance
        for (int i = 1; i < blocksInPath.size(); i++) {
            if (minDistance > trajectory.start().distance(blocksInPath.get(i).collisionPoint())) {
                minDistance = trajectory.start().distance(blocksInPath.get(i).collisionPoint());
                closestDistance = blocksInPath.get(i);
            }
        }
        return closestDistance;
    }

    /**
     *  Checks which collidable items are on the trajectory line.
     /* @param trajectory the line we check intersection with collidable items
     * @return ArrayList of all the collidable item intersect with trajectory.
     */
    public List<CollisionInfo> intersectTrajectory(Line trajectory) {
        List<CollisionInfo> collidablesOnTrajectory = new ArrayList<CollisionInfo>();
        Point intersect; //saves the intersection points

        //runs all over the blocks
        for (int i = 0; i < this.collidablesList.size(); i++) {
            //if intersection was found, adding it to the new List
            intersect = trajectory.closestIntersectionToStartOfLine(
                    (this.collidablesList.get(i).getCollisionRectangle()));
            if (intersect != null) {
                collidablesOnTrajectory.add(new CollisionInfo(intersect, this.collidablesList.get(i)));
            }
        }
        return collidablesOnTrajectory;
    }

    /**
     * Removes collidable from the game.
     * @param c the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
    collidablesList.remove(c);
    }
}