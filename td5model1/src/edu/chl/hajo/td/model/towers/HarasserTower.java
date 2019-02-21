package edu.chl.hajo.td.model.towers;

import edu.chl.hajo.td.model.towers.bullets.AbstractBullet;
import edu.chl.hajo.td.model.towers.bullets.BasicBullet;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;

import java.util.ArrayList;

import static edu.chl.hajo.td.model.TowerDefence.TENTH_SEC;
import static java.lang.Math.sqrt;
import static javafx.scene.paint.Color.LIGHTGREEN;

public class HarasserTower extends AbstractTower {
    
    public HarasserTower(Point2D pos){
        this.pos = pos;
        this.width = 20;
        this.height = 30;
        this.color = LIGHTGREEN;

        this.range = 50;
        this.coolDown = TENTH_SEC/20;
        this.firePower = 5;
        this.bulletPrototype = new BasicBullet(new Point2D(0,0), new Vector2D(0,0));

        this.INIT_DIR = new Vector2D(range/sqrt(2),range/sqrt(2), false);
        this.bullets = new ArrayList<AbstractBullet>(); 
    }
}