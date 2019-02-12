package edu.chl.hajo.td.model;

import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*

    Class representing the full Game

    - GUI will call methods on this class (only)
    - GUI need some getters to display the model (parts of)

 */
public class TowerDefence {

    // Global constants
    public static final int TILE_SIZE = 32;  // 32x32 pixels
    public static final int GAME_SIZE = 20;  // 20x20 tiles
    public static final double GAME_HEIGHT = TILE_SIZE * GAME_SIZE; // 640
    public static final double GAME_WIDTH = GAME_HEIGHT;

    public static final long ONE_SEC = 1_000_000_000;   // Convenient
    public static final long TENTH_SEC = 100_000_000;

    @Getter
    private int points;
    @Getter
    private int damage;

    @Getter
    private final TDMap map = null;

    // Update the model
    public void update(long now) {
            // TODO
    }


    // TODO


    // ------------ Conversions for GUI ------------------

    public TDTile getTile(double x, double y) {
        return map.getTileFor(x, y);
    }

    public Point2D getPosForTile(TDTile t) {
        return map.getPosFor(t);
    }

    // TODO


}
