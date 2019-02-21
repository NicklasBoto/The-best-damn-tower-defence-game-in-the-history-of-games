package edu.chl.hajo.td.model.towers.bullets;

import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;

import javafx.scene.paint.Color;

public class BasicBullet extends AbstractBullet {
    public BasicBullet (Point2D pos, Vector2D dir) {
        this.width = 10;
        this.height = 10;
        this.speed = 1;
        this.color = Color.RED;
        this.pos = pos;
        this.dir = dir;
    }

    public BasicBullet copy(Point2D pos, Vector2D dir) {
        return new BasicBullet(pos, dir);
    }
}
