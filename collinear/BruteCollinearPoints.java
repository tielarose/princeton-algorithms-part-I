/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
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

        // check for collinear points
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();

        for (int i = 0; i < pointsCopy.length - 3; i++)
            for (int j = i + 1; j < pointsCopy.length - 2; j++)
                for (int k = j + 1; k < pointsCopy.length - 1; k++)
                    for (int m = k + 1; m < pointsCopy.length; m++) {
                        double slope1 = pointsCopy[i].slopeTo(pointsCopy[j]);
                        double slope2 = pointsCopy[i].slopeTo(pointsCopy[k]);
                        double slope3 = pointsCopy[i].slopeTo(pointsCopy[m]);
                        if (slope1 == slope2 && slope2 == slope3) {
                            LineSegment currSegment = new LineSegment(pointsCopy[i], pointsCopy[m]);
                            if (!segmentsList.contains(currSegment))
                                segmentsList.add(currSegment);
                        }
                    }

        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);


    }

    public int numberOfSegments() {
        return segments.length;

    }

    public LineSegment[] segments() {
        return segments;
    }
}