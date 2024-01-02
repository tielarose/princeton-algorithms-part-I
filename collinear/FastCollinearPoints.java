import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
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

        // sort the points according to the slope they make with p (origin point)
        ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();

        // make another copy of the points array
        Point[] pointsCopy2 = pointsCopy.clone();
        // loop through the original sorted array, and re-sort the new copy based on
        // each point's slope with the "origin" point
        for (Point origin : pointsCopy) {
            Arrays.sort(pointsCopy2, origin.slopeOrder());
            // check for collinear points
            int left = 1;
            int right = 1;
            while (right < pointsCopy2.length - 1) { // need ending condition here
                while (pointsCopy2[0].slopeTo(pointsCopy[left]) != pointsCopy2[0].slopeTo(
                        pointsCopy[left + 1]))
                    if (left < pointsCopy2.length - 2)
                        left++;
                    else break;
                right = left;
                while (pointsCopy2[0].slopeTo(pointsCopy2[right]) == pointsCopy2[0].slopeTo(
                        pointsCopy2[right + 1]))
                    if (right < pointsCopy2.length - 2)
                        right++;
                    else break;
                if (right - left >= 2) {
                    segmentsList.add(new LineSegment(origin, pointsCopy2[right]));
                }
                left = right + 1;
                right++;
            }
            // I think this will create duplicate segments
            // I'm also not certain that the last point in this order is the farthest (ie captures
            // the whole line segment

        }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }

    public int numberOfSegments() {
        return segments.length;

    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}