package edu.chl.hajo.td.model.renderable;

import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

public abstract class Renderable {
    @Getter
    @Setter
    protected Point2D pos;
    @Getter
    @Setter
    protected Vector2D dir;
    @Getter
    protected int width;
    @Getter
    protected int height;
    @Getter
    protected Color color;
}