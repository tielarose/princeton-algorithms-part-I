import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private int size;
    private Node root;

    private class Node {
        private Point2D p;
        private Node left;
        private Node right;

        private Node(Point2D point) {
            p = point;
            left = null;
            right = null;
        }
    }
    
    // construct an empty set of points
    public KdTree() {
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        root = insert(root, p, 0);

    }

    private Node insert(Node rootNode, Point2D newPoint, int level) {
        if (rootNode == null) {
            size++;
            return new Node(newPoint);
        }

        int comparison = comparePoints(newPoint, rootNode.p, level);

        if (comparison < 0) {
            rootNode.left = insert(rootNode.left, newPoint, level + 1);
        } else {
            rootNode.right = insert(rootNode.right, newPoint, level + 1);
        }

        return rootNode;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return contains(root, p, 0) != null;
    }

    private Point2D contains(Node rootNode, Point2D targetPoint, int level) {
        if (rootNode == null)
            return null;

        int comparison = comparePoints(targetPoint, rootNode.p, level);

        if (comparison == 0) {
            return rootNode.p;
        } else if (comparison < 0) {
            return contains(rootNode.left, targetPoint, level + 1);
        } else {
             return contains(rootNode.right, targetPoint, level + 1);
        }
    }

    // draw all points to standard draw
    public oid draw() {
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
    }

    private int compare(double a, double b) {
        if (a > b)
            return 1;
        else if (a < b)
            return -1;
        else
            return 0;
    }

    private int comparePoints(Point2D a, Point2D b, int treeLevel) {
        int result;
        double ax = a.x();
        double ay = a.y();
        double bx = b.x();
        double by = b.y();

        if (treeLevel % 2 == 0) { // even levels, compare x coordinates
            result = compare(ax, bx);

            if (result == 0) // if x values are the same, break tie with y values
                result = compare(ay, by);

            return result;
        } else { // odd levels, compare y coordinates
            result = compare(ay, by);

            if (result == 0)  // if y values are the same, break tie with x values
                result = compare(ax, bx);

            return result;

        }
    }
    
    public static void main(String[] args) {

    }
}
