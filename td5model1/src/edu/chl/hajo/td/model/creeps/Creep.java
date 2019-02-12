package edu.chl.hajo.td.model.creeps;

import edu.chl.hajo.td.model.Path;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import lombok.Getter;
import lombok.Setter;

import static edu.chl.hajo.td.model.TowerDefence.TILE_SIZE;

/*
 *     A basic creep (something that follows a path)
 *     - Follows exactly one path
 *     - Will damage you if arriving at path end
 *     - May be killed by some tower placed by you
 */
public class Creep {

    @Setter
    @Getter
    private Point2D pos;    // Center position

    @Getter
    private final double width = -1; // Upper right corner: x - width / 2
    @Getter
    private final double height = -1;

    @Getter
    @Setter
    private Vector2D dir;  // Normalized direction for moving
    
    @Setter
    @Getter
    private double speed;

    @Getter
    private final Path path;

    @Getter
    private int currentPoint;
    
    /*
    @Getter
    @Setter
    private int hp;  // Current health
    @Getter
    private final int maxHp;      // Needed for % display in GUI
    @Getter
    private final int killPoints;  // Points to player when killed
    @Getter
    private final int damage;      // Damage caused when arriving at (non existing) base of player
    */

    public Creep(Path path, double speed) {
        this.path = path;
        this.speed = speed;
        this.pos = path.getPoints().get(0);
        this.currentPoint = 0;
    }

    public void move(){
        Point2D nextPoint = path.getPoints().get(currentPoint);
        Vector2D vector = new Vector2D(nextPoint.getX() - pos.getX(), nextPoint.getY() - pos.getY());
        vector.scale(speed);
        pos.add(vector);

        if (nextPoint.distance(pos) < 0.1) {
            currentPoint++;
        }

    }


}
