package edu.chl.hajo.td.util;

import lombok.Getter;

import java.util.Objects;

import static java.lang.Math.sqrt;

/*
 *    An immutable position in the plane
 *
 *    *** Nothing to do here ***
 *
 */
public class Point2D {
    @Getter
    private final double x;
    @Getter
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D other) {
        this(other.x, other.y);
    }

    public Point2D add(Vector2D v) {
        return new Point2D(x + v.getX(), y + v.getY());
    }

    // Is other point epsilon close to this?
    public boolean epsilonEquals(Point2D other, double epsilon) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        return distance(other) < epsilon;
    }

    public double distance(Point2D other) {
        return sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Double.compare(point2D.x, x) == 0 &&
                Double.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
