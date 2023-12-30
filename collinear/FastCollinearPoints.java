import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        // corner cases
        // input is null
        if (points == null)
            throw new IllegalArgumentException("Input is null.");

        // contains a null point
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("At least one point is null.");
        }

        // contains a duplicate point
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        for (int i = 1; i < pointsCopy.length; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i - 1]) == 0)
                throw new IllegalArgumentException("Input contains duplicate points.");
        }
    }

    public int numberOfSegments() {
        return segments.length;

    }

    public LineSegment[] segments() {
        return segments;
    }          // the line segments
}