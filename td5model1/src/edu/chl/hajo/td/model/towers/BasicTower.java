package edu.chl.hajo.td.model.towers;

import edu.chl.hajo.td.model.towers.bullets.AbstractBullet;
import edu.chl.hajo.td.model.towers.bullets.BasicBullet;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;

import java.util.ArrayList;

import static edu.chl.hajo.td.model.TowerDefence.TENTH_SEC;
import static javafx.scene.paint.Color.BLUE;
import static java.lang.Math.sqrt;

public class BasicTower extends AbstractTower {

    public BasicTower(Point2D pos) {
        this.pos = pos;
        this.width = 40;
        this.height = 40;
        this.color = BLUE;

        this.range = 100;
        this.coolDown = TENTH_SEC*4;
        this.firePower = 15;
        this.bulletPrototype = new BasicBullet(new Point2D(0,0), new Vector2D(0,0));

        this.INIT_DIR = new Vector2D(range/sqrt(2),range/sqrt(2), false);
        this.dir = INIT_DIR;
        this.bullets = new ArrayList<>();
    }
}

