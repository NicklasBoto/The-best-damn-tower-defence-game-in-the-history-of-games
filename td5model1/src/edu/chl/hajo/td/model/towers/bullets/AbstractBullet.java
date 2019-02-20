package edu.chl.hajo.td.model.towers.bullets;

import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;

import javafx.scene.paint.Color;
import lombok.Getter;

public class AbstractBullet {
    @Getter
    protected int size;

    @Getter
    protected double speed;

    @Getter
    protected Color color;

    @Getter
    protected Point2D pos;

    @Getter
    protected Vector2D dir;

    public void move() {
        Vector2D vector = dir.scale(speed);
        this.pos = this.pos.add(dir);
    }
}
