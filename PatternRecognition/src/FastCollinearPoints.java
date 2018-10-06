import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Argument in the constructor is null");
        if (containsNullPoint(points)) throw new IllegalArgumentException("points[] contains a null point");

        Point[] tmpPoints = points.clone();
        int n = tmpPoints.length;
        segments = new ArrayList<>();

        Arrays.sort(tmpPoints);
        if (containsRepeatedPoints(tmpPoints)) throw new IllegalArgumentException("points[] contains a repeated point");

        // Sort the points according to the slopes they makes with i-th point.
        // Looking for all subarrays of >= 3 length with the same values in all cells
        // Variables last and first point out the first and last indices of the subarray in the points[]
        // All such subarrays are arrays of collinear points
        for (int i = 0; i < n - 2; ++i) {
            Arrays.sort(tmpPoints);
            Arrays.sort(tmpPoints, tmpPoints[i].slopeOrder());
            Point curr = tmpPoints[0];

            int first = 1;
            int last = 2;

            while (last < n) {
                while (last < tmpPoints.length
                        && Double.compare(curr.slopeTo(tmpPoints[first]), curr.slopeTo(tmpPoints[last])) == 0) {
                    ++last;
                }

                if (last - first >= 3 && curr.compareTo(tmpPoints[first]) < 0)
                    segments.add(new LineSegment(curr, tmpPoints[last - 1]));
                first = last;
                ++last;
            }



        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    private boolean containsNullPoint(Point[] points) {
        for (Point p: points)
            if (p == null) return true;
            return false;
    }

    private boolean containsRepeatedPoints(Point[] points) {
        int n = points.length;
        for (int i = 0; i < n - 1; ++i)
            if (points[i].compareTo(points[i + 1]) == 0) return true;
            return false;
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
