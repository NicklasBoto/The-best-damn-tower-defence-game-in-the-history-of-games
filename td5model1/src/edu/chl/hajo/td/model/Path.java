package edu.chl.hajo.td.model;

import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
 *   A path for creeps (waves) to follow
 */
public class Path {
    
    @Getter
    private final int id;

    @Getter
    private final List<Point2D> points;

    @Getter
    private final int TILE_SIZE;

    public Path (int id, List<String> lS, int TILE_SIZE) {
        this.id = id;
        this.TILE_SIZE = TILE_SIZE;

        ArrayList<Point2D> pointList = new ArrayList<>();
        for (String s : lS) {
            String[] parts = s.split(",");

            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            Point2D point = new Point2D(x, y);

            pointList.add(point);
        }

        this.points = pointList;
    }
    //WHY
    public Point2D get(int i){
        return points.get(i);
    }

    // TODO

}
