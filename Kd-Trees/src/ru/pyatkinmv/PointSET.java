package ru.pyatkinmv;

public class PointSET {

    // construct an empty set of points
    public PointSET() {

    }

    // is the set empty?
    public boolean isEmpty() { //TODO
        return false;
    }

    // number of points in the set
    public int size() { //TODO
        return 0;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) { //TODO

    }

    // does the set contain point p?
    public boolean contains(Point2D p) { //TODO
        return false;
    }

    // draw all points to standard draw
    public void draw() { //TODO

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) { //TODO
        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) { //TODO
        return p;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) { //TODO

    }

}
