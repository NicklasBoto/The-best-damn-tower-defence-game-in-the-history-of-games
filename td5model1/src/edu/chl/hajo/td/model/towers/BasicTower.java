package edu.chl.hajo.td.model.towers;

import edu.chl.hajo.td.model.towers.bullets.BasicBullet;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;

import static edu.chl.hajo.td.model.TowerDefence.TENTH_SEC;

public class BasicTower extends AbstractTower {

    public BasicTower(Point2D pos) {
        this.pos = pos;
        this.width = 40;
        this.height = 40;
        this.range = 10;
        this.coolDown = TENTH_SEC;
        this.firePower = 15;
        this.bulletPrototype = new BasicBullet(new Point2D(0,0), new Vector2D(0,0));
    }
}