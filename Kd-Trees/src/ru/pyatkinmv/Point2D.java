package ru.pyatkinmv;

public class Point2D implements Comparable<Point2D> {
    private double x;
    private double y;

    // construct the point (x, y)
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // x-coordinate
    public  double x() {
        return x;
    }

    // y-coordinate
    public  double y() {
        return y;
    }

    // Euclidean distance between two points
    public  double distanceTo(Point2D that) {
        return Math.sqrt(distanceSquaredTo(that));
    }

    // square of Euclidean distance between two points
    public  double distanceSquaredTo(Point2D that) {
        return (that.x - x) * (that.x - x) + (that.y - y) * (that.y - y);
    }

    // for use in an ordered symbol table
    public int compareTo(Point2D that) { //TODO
        return  0;
    }

    // does this point equal that object?
    public boolean equals(Object that) {
        if (that == null || !that.getClass().equals(getClass())) return false;
        if (that == this) return true;

        Point2D point = (Point2D) that;

        if (x != point.x || y != point.y) return false;

        return true;
    }

    // draw to standard draw
    public void draw() { //TODO

    }

    // string representation
    public String toString() { //TODO
        return null;
    }

}