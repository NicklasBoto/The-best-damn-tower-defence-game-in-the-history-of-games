package edu.chl.hajo.td.model.towers.bullets;

import edu.chl.hajo.td.model.Renderable.Renderable;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;

import javafx.scene.paint.Color;
import lombok.Getter;

public class AbstractBullet extends Renderable {
    @Getter
    protected double speed;

    public void move() {
        Vector2D vector = dir.scale(speed);
        this.pos = this.pos.add(dir);
    }

    public AbstractBullet copy ();
}
