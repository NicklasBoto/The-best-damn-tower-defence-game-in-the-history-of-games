package edu.chl.hajo.td.model.creeps;
import edu.chl.hajo.td.model.Path;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import javafx.scene.paint.Color;

public class BasicCreep extends AbstractCreep {
    public BasicCreep(Path path) {
        this.path = path;
        this.speed = 0.5;
        this.width = 10;
        this.height = 10;
        this.color = Color.RED;
        this.pos = path.getPoints().get(0);
        this.currentPoint = 0;
        this.dir = new Vector2D(0,0);
        this.finished = false;
        this.dead = false;
        this.hp = 100;
        this.maxHp = 100;
        this.killPoints = 10;
        this.damage = 10;
    }

    @Override
    public AbstractCreep copy()
    {
        return new BasicCreep(this.path);
    }
}