package edu.chl.hajo.td.model.towers;

import edu.chl.hajo.td.model.TowerDefence;
import edu.chl.hajo.td.model.renderable.Renderable;
import edu.chl.hajo.td.model.Wave;
import edu.chl.hajo.td.model.creeps.AbstractCreep;
import edu.chl.hajo.td.model.towers.bullets.AbstractBullet;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import java.util.ArrayList;

import edu.chl.hajo.td.view.TowerDefenceGUI;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class AbstractTower extends Renderable {
    @Getter
    protected double range;    // Max distance to fire
    @Getter
    protected long coolDown;  // Time before next shoot

    @Getter
    protected int firePower;

    @Getter
    protected ArrayList<AbstractBullet> bullets;

    private boolean resetTimer = true;
    private long timer;

    protected Vector2D INIT_DIR;
    protected AbstractBullet bulletPrototype;

    public void update(List<Wave> ws, long now){
        updateBullets(ws);

        if (resetTimer) {
            timer = now;
            resetTimer = false;
        }
        if (now-timer>coolDown) {
            AbstractCreep c = getClosestCreep(ws);
            if (c!=null) {
                this.dir = targetPrediction(c);
                if (new Vector2D(this.getPos(), c.getPos()).getLen()<range) { //WIP only shoot if enemy in range
                    shoot();
                }
            }

            resetTimer = true;
        }



    }

    public Vector2D targetPrediction(AbstractCreep c) {
        int originalPoint = c.getCurrentPoint();
        Point2D originalPos = c.getPos();

        int steps = 0;
        double smallestError = 1000000.0;
        Vector2D bestDir = new Vector2D(0,0);
        while(new Vector2D(this.pos, c.getPos()).getLen() < range) {
            c.move();
            steps ++;

            Vector2D disToCreepAtThatTime = new Vector2D(this.getPos(), c.getPos(), false);
            double error = Math.abs((disToCreepAtThatTime.getLen()/bulletPrototype.getSpeed())-steps);
            if (Math.round(error) == 1) {
                smallestError = error;
                bestDir = disToCreepAtThatTime;
            }
        }

        c.setPos((originalPos));
        c.setCurrentPoint(originalPoint);
        return bestDir;
    }

    public AbstractCreep getClosestCreep(List<Wave> ws) {
        AbstractCreep closest;
        closest = null; //Null if closest
        this.dir = INIT_DIR;
        for (Wave wave : ws){
            List<AbstractCreep> creeps = wave.getCreeps();
            for (AbstractCreep creep : creeps){
                Vector2D aim = new Vector2D(pos, creep.getPos(), false);
                if (aim.getLen() < dir.getLen() && aim.getLen() <= this.range){
                    closest = creep; //Copy of creep
                    //this.dir = aim;
                }
            }
        }
        return closest;
    }

    public void updateBullets(List<Wave> ws) {
        for (int i = bullets.size()-1; i >= 0; i --) {
            AbstractBullet b = bullets.get(i);

            //Move bullets
            b.move();

            //Remove out of range bullets
            Point2D bPos = b.getPos();
            if (bPos.getX() > TowerDefence.GAME_WIDTH || bPos.getX() < 0 || bPos.getY() > TowerDefence.GAME_HEIGHT || bPos.getY() < 0) {
                bullets.remove(i);
            }

            //Handle collision. AABB collison formula.
            for (Wave w : ws) {
                for (AbstractCreep c : w.getCreeps()) {
                    if(bPos.getX() < c.getPos().getX() + c.getWidth() &&
                            bPos.getX() + b.getWidth() > c.getPos().getX() &&
                            bPos.getY() < c.getPos().getY() + c.getHeight() &&
                            bPos.getY() + b.getWidth() > c.getPos().getY()) {
                        c.setHp(c.getHp()-firePower);
                        bullets.remove(i);
                    }
                }
            }

        }
    }

    public void shoot() {
        Vector2D shootDir = new Vector2D(this.pos, this.pos.add(this.dir)); //Normalized
        bullets.add(bulletPrototype.copy(this.getPos(), shootDir));
    }
}
