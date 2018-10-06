import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    // private Point[] points;
    private List<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        segments = new ArrayList<>();
        int n = points.length;

        for (int i = 0; i < n - 3; ++i)
            for (int j = i + 1; j < n - 2; ++j )
                for (int k = j + 1; k < n - 1; ++k)
                    for (int l = k + 1; l < n ; ++l) {
                        double tmpSlope = points[i].slopeTo(points[j]);
                        if (tmpSlope == points[i].slopeTo(points[k]) && tmpSlope == points[i].slopeTo(points[l])) {
                            Point[] tmpPoints = {points[i], points[j], points[k], points[l]};
                            Arrays.sort(tmpPoints);
                            segments.add(new LineSegment(tmpPoints[0], tmpPoints[3]));
                        }
                    }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    // возвращает все Linesegments, состоящие из 4-х элементов с учетом условий
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}