package edu.chl.hajo.td.model.towers;

import edu.chl.hajo.td.model.Wave;
import edu.chl.hajo.td.model.creeps.AbstractCreep;
import edu.chl.hajo.td.model.towers.bullets.AbstractBullet;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class AbstractTower {
    @Setter
    @Getter
    protected Point2D pos;    // Center position

    @Getter
    protected double width; // Upper right corner: x - width / 2
    @Getter
    protected double height;  // Upper right corner: y - height / 2


    @Getter
    protected double range;    // Max distance to fire
    @Getter
    protected long coolDown;  // Time before next shoot

    @Getter
    @Setter
    protected long coolDownTimer;            
            
    @Getter
    protected int firePower;


    protected static Vector2D INIT_DIR = new Vector2D(200, 200);
    @Getter
    protected Vector2D dir = INIT_DIR;

    /*
    abstract protected class Bullet {
        @Getter
        protected int size;

        @Getter
        protected double speed;

        @Getter
        protected Point2D pos;

        @Getter
        protected Point2D dir;

        public Bullet (Point2D pos, Point2D dir) {
            this.pos = pos;
            this.dir = dir;
        }
    }*/

    protected AbstractBullet bulletPrototype;
    private List<AbstractBullet> bullets;

    public void update(List<Wave> ws){
        AbstractCreep closest;

        this.dir = INIT_DIR;                        
        for (Wave wave : ws){
            List<AbstractCreep> creeps = wave.getCreeps();
            for (AbstractCreep creep : creeps){
                Vector2D aim = new Vector2D(pos, creep.getPos(), false); 
                if (aim.getLen() < dir.getLen() && aim.getLen() <= this.range){
                    closest = creep;
                    this.dir = aim;
                }
            }
        }
    }
}
