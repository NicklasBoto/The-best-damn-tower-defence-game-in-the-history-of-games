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
    private final double width; // Upper right corner: x - width / 2
    @Getter
    private final double height;

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

    private boolean finished;

    public boolean getFinished(){
        return finished;
    }

    private boolean dead;

    public boolean getDead(){
        return dead;
    }

    @Getter
    @Setter
    private int hp;  // Current health
/*
    @Getter
    private final int maxHp;      // Needed for % display in GUI

    @Getter
    private final int killPoints;  // Points to player when killed

    @Getter
    private final int damage;      // Damage caused when arriving at (non existing) base of player
 */

    public Creep(Path path, double speed, int width, int height) {
        this.path = path;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.pos = path.getPoints().get(0);
        this.currentPoint = 0;
        this.dir = new Vector2D(0,0);
        this.finished = false;
        this.dead = false;
        this.hp = 1000;
    }

    public Creep(Path path){
        this(path, 20, 10, 10);
    }

    public Creep(Path path, double speed) {
        this(path, speed, 1, 1);
    }

    public void move(){
        Point2D nextPoint = path.getPoints().get(currentPoint + 1);
        Vector2D vector = new Vector2D(pos, nextPoint);
        vector.scale(speed);
        this.dir = vector;
        this.pos = this.pos.add(vector);

        if (nextPoint.distance(pos) < 0.1) {
            currentPoint++;
        }
        if (currentPoint == path.getPoints().size() - 1) {
            finished = true;
        }

        if (hp <= 0){
            dead = true;
        }
    }

}
