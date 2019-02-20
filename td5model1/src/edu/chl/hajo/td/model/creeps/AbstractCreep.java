package edu.chl.hajo.td.model.creeps;

import edu.chl.hajo.td.model.Path;
import edu.chl.hajo.td.model.renderable.Renderable;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import static edu.chl.hajo.td.model.TowerDefence.TILE_SIZE;

/*
 *     A basic creep (something that follows a path)
 *     - Follows exactly one path
 *     - Will damage you if arriving at path end
 *     - May be killed by some tower placed by you
 */
public abstract class AbstractCreep extends Renderable {

    @Setter
    @Getter
    protected double speed;

    @Getter
    protected Path path;

    @Getter
    protected int currentPoint;

    @Getter
    protected boolean finished;

    @Getter
    protected boolean dead;

    @Getter
    @Setter
    protected int hp;  // Current health

    @Getter
    protected int maxHp;      // Needed for % display in GUI

    @Getter
    protected int killPoints;  // Points to player when killed

    @Getter
    protected int damage;      // Damage caused when arriving at (non existing) base of player

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

    public abstract AbstractCreep copy ();
}
