package edu.chl.hajo.td.view;

import edu.chl.hajo.td.model.*;

import edu.chl.hajo.td.model.creeps.Creep;
import edu.chl.hajo.td.util.Point2D;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.DelayQueue;

import static edu.chl.hajo.td.model.TowerDefence.*;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.DARKORANGE;
import static javafx.scene.paint.Color.RED;


/*
       Simplified GUI for testing and exploring
       the object oriented model

 */
public class TowerDefenceGUI extends Application {


    // Objects we're working with right now, used below in step 1-4
    private TowerDefence td;
    private Creep c;
    private Wave w;
    private Path p;


    // TODO STEP 1
    /*
    @Override
    public void init() {
        List<String> strPts = Arrays.asList(
                "0,3", "3,3", "3,9", "8,9", "8,4", "12,4",
                "12,12", "3,12", "3,17", "17,17",
                "17,6", "20,6");
        p = new Path(0, strPts, 20);
        c = new Creep(p, 30.0, 10, 10);
    }

    private void update(long now) {
        c.move();
    }

    private void render() {
        clearScreen();
        renderCreep(c);
        renderPath(p);
    }
    */
    /*
    //  TODO STEP 2
    @Override
    public void init() {
        List<String> strPts = Arrays.asList(
                "0,3", "3,3", "3,9", "8,9", "8,4", "12,4",
                "12,12", "3,12", "3,17", "17,17",
                "17,6", "20,6");
        p = new Path(0, strPts, TILE_SIZE);
        c = new Creep(p);
        w = new Wave(5, TENTH_SEC, ONE_SEC, c);
    }

    private void update(long now) {
        w.equalsmove();
        w.spawn(now);
    }

    private void render() {
        clearScreen();
        renderWave(w);
        renderPath(p);
    }*/

    // TODO STEP 3

    @Override
    public void init() {
        TDTile[][] logicalTile = new TDTile[20][20];
        // NOTE: y is row, x is col.
        for (int y = 0; y < logicalTile.length; y++) {
            for (int x = 0; x < logicalTile.length; x++) {
                logicalTile[y][x] = new TDTile();
            }
        }
        TDMap map = new TDMap(logicalTile, TILE_SIZE);
        List<String> strPts = Arrays.asList(
                "0,3", "3,3", "3,9", "8,9", "8,4", "12,4",
                "12,12", "3,12", "3,17", "17,17",
                "17,6", "20,6");
        p = new Path(0, strPts, TILE_SIZE);
        c = new Creep(p);
        Wave wave = new Wave(5, TENTH_SEC, ONE_SEC, c);
        List<Wave> waves = Arrays.asList(wave);
        td = new TowerDefence(map, waves);

    }

    private void update(long now) {
        td.update(now);
    }

    private void render() {
        clearScreen();
        render(td);
        renderPath(p);
    }

    /*
     // TODO STEP 4
    @Override
    public void init() {
        TDTile[][] logicalTile = new TDTile[20][20];
        // NOTE: y is row, x is col.
        for (int y = 0; y < logicalTile.length; y++) {
            for (int x = 0; x < logicalTile.length; x++) {
                logicalTile[y][x] = new TDTile();
            }
        }
        TDMap map = new TDMap(logicalTile, TILE_SIZE);
        List<String> strPts = Arrays.asList(
                "0,3", "3,3", "3,9", "8,9", "8,4", "12,4",
                "12,12", "3,12", "3,17", "17,17",
                "17,6", "20,6");
        p = new Path(0, strPts, TILE_SIZE);
        c = new Creep(p);
        Wave wave = new Wave(5, 100_000_000,
                1_000_000_000, c);

        List<Wave> waves = Arrays.asList(wave);

        td = new TowerDefence(map, waves);

        // Add tower at center for some tile
        td.addTower(new Tower(
                new Point2D(4 * TILE_SIZE + TILE_SIZE / 2, 6 * TILE_SIZE + TILE_SIZE / 2)));
    }

    private void update(long now) {
        td.update(now);
    }

    private void render() {
        clearScreen();
        render(td);
    }
    */
    // ------------------ Render -----------------------------

    private void render(TowerDefence td) {
        clearScreen();
        for (Wave wave : td.getWaves()) {
            renderWave(wave);
        }
    }

    private void renderWave(Wave wave) {
        for (Creep creep : wave.getCreeps()){
            renderCreep(creep);
        }
    }

    private void renderPath(Path path) {
        gc.setFill(DARKORANGE);
        for (Point2D p : path.getPoints()) {
            double xTopLeft = p.getX() - 2;
            double yTopLeft = p.getY() - 2;
            gc.fillOval(xTopLeft, yTopLeft, 12, 12);
        }
        gc.setFill(BLACK);
    }


    private void renderCreep(Creep c) {
        gc.setFill(RED);
        fillRect(c.getPos(), c.getWidth(), c.getHeight());
        strokeLine(c.getPos(), c.getPos().add(c.getDir().scale(12)));

    }

    // ------------------ JavFX GUI Nothing to do below  -----------------------------


    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defence Model");
        primaryStage.setWidth(GAME_WIDTH);
        primaryStage.setHeight(GAME_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
                render();
            }
        };
        timer.start();
    }

    /*
    private void render(TowerDefence td) {
        clearScreen();
        renderGrid(td.getMap().getTiles());
        td.getWaves().forEach(this::renderWave);
    }


    private void renderPath(Path path) {
        gc.setFill(DARKORANGE);
        for (Point2D p : path.getPoints()) {
            double xTopLeft = p.getX() - 2;
            double yTopLeft = p.getY() - 2;
            gc.fillOval(xTopLeft, yTopLeft, 6, 6);
        }
        gc.setFill(BLACK);
    }

    private void renderWave(Wave wave) {
        for (Creep c : wave.getCreeps()) {
            renderCreep(c);
        }
    }

    private void renderCreep(Creep c) {
        gc.setFill(RED);
        fillRect(c.getPos(), c.getWidth(), c.getHeight());
        strokeLine(c.getPos(), c.getPos().add(c.getDir().scale(15)));

    }
*/
    private void renderGrid(TDTile[][] tiles) {
        gc.setLineWidth(1.0);
        for (int i = 0; i < tiles.length; i++) {
            gc.strokeLine(TILE_SIZE * i + 0.5, 0,
                    TILE_SIZE * i + 0.5, GAME_HEIGHT);
        }
        for (int i = 0; i < tiles.length; i++) {
            gc.strokeLine(0, TILE_SIZE * i + 0.5,
                    GAME_WIDTH, TILE_SIZE * i + 0.5);
        }
    }

    private void fillRect(Point2D pos, double width, double height) {
        double xTopLeft = pos.getX() - width / 2;
        double yTopLeft = pos.getY() - height / 2;
        gc.fillRect(xTopLeft, yTopLeft, width, height);
    }

    private void fillOval(Point2D pos, double width, double height) {
        double xTopLeft = pos.getX() - width / 2;
        double yTopLeft = pos.getY() - height / 2;
        gc.fillOval(xTopLeft, yTopLeft, width, height);
    }

    private void strokeLine(Point2D start, Point2D end) {
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    private void clearScreen() {
        gc.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
    }

}
