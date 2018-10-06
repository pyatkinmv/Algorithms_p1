import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> segments;
    private List<Double> slopes;
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // this.points = points;
        slopes = new ArrayList<>();
        segments = new ArrayList<>();
        int n = points.length;

        for (int i = 0; i < n; ++i) {
            Arrays.sort(points);
            Point p = points[i];
            Arrays.sort(points, p.slopeOrder());

            int low = 1;
            int high = 1;
            while (high < n - 1) {
                ++high;
                if (p.slopeTo(points[low]) != p.slopeTo(points[high])) {
                    if (high - low >= 3) {

                        Point[] tmpPoints = new Point[high - low + 1];
                        tmpPoints[0] = points[0];

                        for (int k = low; k < high; ++k)
                            tmpPoints[k - low + 1] = points[k];

                        Arrays.sort(tmpPoints);
                        Double slope = p.slopeTo(points[low]);
                        segments.add(new LineSegment(tmpPoints[0], tmpPoints[high - low]));

                    }
                    low = high;
                }
            }
        }
    }
        // Есть отсортированный массив, нужно найти для каждого элемента число вхождений
        // вернуть надо "интервалы" от наименьшего индекса данного элемента до наибольшего индекса
        // передаем
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