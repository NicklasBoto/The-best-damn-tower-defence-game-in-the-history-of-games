package edu.chl.hajo.td.util;

import lombok.Getter;

import java.util.Objects;

import static java.lang.Math.sqrt;

/*
 *  A (mostly) normalized immutable vector in 2D
 *  Use for all kinds of directions (facings)
 *
 *   *** Nothing to do here ***
 */
public class Vector2D {

    public static final Vector2D VECT_ZERO = new Vector2D(0, 0);
    @Getter
    private final double x;
    @Getter
    private final double y;

    // NOTE: Private only for internal use
    private Vector2D(double x, double y, boolean normalize) {
        if (normalize) {
            double len = length(x, y);
            if (len > 0) {
                this.x = x / len;
                this.y = y / len;
            } else {
                this.x = 0;
                this.y = 0;
            }
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public Vector2D(double x, double y) {
        this(x, y, true);
    }

    public Vector2D(Point2D p1, Point2D p2) {
        this(p2.getX() - p1.getX(), p2.getY() - p1.getY(), true);
    }

    // Result NOT normalized
    public Vector2D scale(double k) {
        return new Vector2D(k * x, k * y, false);
    }

    private double length(double x, double y) {
        return sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(vector2D.x, x) == 0 &&
                Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
