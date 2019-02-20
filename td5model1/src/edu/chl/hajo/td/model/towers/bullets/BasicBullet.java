package edu.chl.hajo.td.model.towers.bullets;

import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;

import javafx.scene.paint.Color;

public class BasicBullet extends AbstractBullet {
    public BasicBullet (Point2D pos, Vector2D dir) {
        this.size = 10;
        this.speed = 20;
        this.color = Color.RED;
        this.pos = pos;
        this.dir = dir;
    }
}
