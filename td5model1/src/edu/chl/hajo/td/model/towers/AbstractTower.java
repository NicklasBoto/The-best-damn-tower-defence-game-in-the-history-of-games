package edu.chl.hajo.td.model.towers;

import edu.chl.hajo.td.model.renderable.Renderable;
import edu.chl.hajo.td.model.Wave;
import edu.chl.hajo.td.model.creeps.AbstractCreep;
import edu.chl.hajo.td.model.towers.bullets.AbstractBullet;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class AbstractTower extends Renderable {
    @Getter
    protected double range;    // Max distance to fire
    @Getter
    protected long coolDown;  // Time before next shoot

    @Getter
    @Setter
    protected long coolDownTimer;

    @Getter
    protected int firePower;

    @Getter
    protected ArrayList<AbstractBullet> bullets;

    protected Vector2D INIT_DIR;
    protected AbstractBullet bulletPrototype;

    public void update(List<Wave> ws){
        updateBullets();

        AbstractCreep closest;

        this.dir = INIT_DIR;
        for (Wave wave : ws){
            List<AbstractCreep> creeps = wave.getCreeps();
            for (AbstractCreep creep : creeps){
                Vector2D aim = new Vector2D(pos, creep.getPos(), false);
                if (aim.getLen() < dir.getLen() && aim.getLen() <= this.range){
                    closest = creep;
                    this.dir = aim;
                    this.shoot();
                }
            }
        }
    }

    public void updateBullets() {
        for (AbstractBullet b : bullets) {
            b.move();
        }
    }

    public void shoot() {
        bullets.add(bulletPrototype.copy(this.getPos(), this.getDir()));
    }
}
