package ru.pyatkinmv;

public class RectHV {

    // construct the rectangle [xmin, xmax] x [ymin, ymax]
    // throw a java.lang.IllegalArgumentException if (xmin > xmax) or (ymin > ymax)
    public RectHV(double xmin, double ymin, double xmax, double ymax) {

    }

    // minimum x-coordinate of rectangle
    public  double xmin() { //TODO
        return 0;
    }

    // minimum y-coordinate of rectangle
    public  double ymin() { //TODO
        return 0;
    }

    // maximum x-coordinate of rectangle
    public  double xmax() { //TODO
        return 0;
    }

    // maximum y-coordinate of rectangle
    public  double ymax() { //TODO
        return 0;
    }

    // does this rectangle contain the point p (either inside or on boundary)?
    public boolean contains(Point2D p) { //TODO
        return false;
    }

    // does this rectangle intersect that rectangle (at one or more points)?
    public boolean intersects(RectHV that) { //TODO
        return false;
    }

    // Euclidean distance from point p to closest point in rectangle
    public  double distanceTo(Point2D p) { //TODO
        return 0;
    }

    // square of Euclidean distance from point p to closest point in rectangle
    public  double distanceSquaredTo(Point2D p) { //TODO
        return 0;
    }

    // does this rectangle equal that object?
    public boolean equals(Object that) { //TODO
        return false;
    }

    // draw to standard draw
    public void draw() { //TODO

    }

    // string representation
    public  String toString() { //TODO
        return null;
    }


}
