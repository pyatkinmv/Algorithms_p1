import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final List<LineSegment> segments;

    // Finds all line segments containing exactly 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Argument in the constructor is null");
        if (containsNullPoint(points)) throw new IllegalArgumentException("points[] contains a null point");
        if (containsRepeatedPoints(points)) throw new IllegalArgumentException("points[] contains a repeated point");

        segments = new ArrayList<>();
        int n = points.length;

        for (int i = 0; i < n - 3; ++i)
            for (int j = i + 1; j < n - 2; ++j)
                for (int k = j + 1; k < n - 1; ++k)
                    for (int p = k + 1; p < n; ++p) {
                        double tmpSlope = points[i].slopeTo(points[j]);

                        if (Double.compare(tmpSlope, points[i].slopeTo(points[k])) == 0
                        && Double.compare(tmpSlope, points[i].slopeTo(points[p])) == 0) {
                            Point[] tmpPoints = { points[i], points[j], points[k], points[p] };
                            Arrays.sort(tmpPoints);
                            segments.add(new LineSegment(tmpPoints[0], tmpPoints[3]));
                        }
                    }
    }

    private boolean containsNullPoint(Point[] points) {
        for (Point p: points)
            if (p == null) return true;
        return false;
    }

    private boolean containsRepeatedPoints(Point[] points) {
        int n = points.length;
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                if (points[i].compareTo(points[j]) == 0) return true;
                return false;
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() { return segments.toArray(new LineSegment[0]); }
}