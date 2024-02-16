import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class KdTree {
    private int size;
    private Node root;
    private double XMIN = 0.0;
    private double XMAX = 1.0;
    private double YMIN = 0.0;
    private double YMAX = 1.0;

    private class Node {
        private Point2D p;
        private Node left;
        private Node right;
        private RectHV rect;

        private Node(Point2D point, RectHV rectangle) {
            p = point;
            left = null;
            right = null;
            rect = rectangle;
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
        root = insert(root, p, 0, XMIN, XMAX, YMIN, YMAX);

    }

    private Node insert(Node rootNode, Point2D newPoint, int level, double xmin, double xmax, double ymin, double ymax) {
        if (rootNode == null) {
            size++;
            return new Node(newPoint, new RectHV(xmin, ymin, xmax, ymax));
        }

        int comparison = comparePoints(newPoint, rootNode.p, level);

        if (comparison < 0) { // new node goes on the left
            // even level, comparing via x-coordinate, curr x is new max
            if (level % 2 == 0) { 
                rootNode.left = insert(rootNode.left, newPoint, level + 1, xmin, ymin, rootNode.p.x(), ymax);
            } 
            // odd level, comparing via y-coordinate, y val is new max
            else { 
                rootNode.left = insert(rootNode.left, newPoint, level + 1, xmin, ymin, xmax, rootNode.p.y());
            }
            
        } else { // new node goes on the right
            // even level, comparing via x-coordinate, curr x is new min
            if (level % 2 == 0) {
                rootNode.right = insert(rootNode.right, newPoint, level + 1, rootNode.p.x(), ymin, xmax, ymax);
            }
            // odd level, comparing via y-coordinate, y val is new min
            else {
                rootNode.right = insert(rootNode.right, newPoint, level + 1, xmin, rootNode.p.y(), xmax, ymax);
            }
            
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
    public void draw() {
        StdDraw.clear();

        drawLine(root, 0);
    }

    private void drawLine(Node node, int level) {
        if (node != null) {
            // draw the line/point of the left child
            drawLine(node.left, level + 1);

            // draw this line/point
            StdDraw.setPenRadius();
            // even levels --> vertical line, draw in red
            // draw a line with this x from min y to max y of this node
            if (level % 2 == 0) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            }
            // odd levels --> horizontal line, draw in blue
            // draw a line with this y from min x to max x of this node
            else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            }

            // draw the point
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.05);
            node.p.draw();

            // draw the line/point of the right child
            drawLine(node.right, level + 1);
        }
    }


    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<Point2D>();

        range(root, rect, stack);

        return stack;
    }

    private void range(Node node, RectHV rect, Stack<Point2D> stack) {
        if (node != null && rect.intersects(node.rect)) {
            if (rect.contains(node.p)) {
                stack.push(node.p);
            }

            range(node.left, rect, stack);
            range(node.right, rect, stack);

        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;
        else {
            Point2D ans = null;
            ans = nearest(root, p, ans);

            return ans;
        }
    }

    private Point2D nearest(Node node, Point2D point, Point2D min) {
        if (node != null) {
            if (min == null) {
                min = node.p;
            }
        }

        if (min.distanceSquaredTo(point) >= node.rect.distanceSquaredTo(point)) {
            if (node.p.distanceSquaredTo(point) < min.distanceSquaredTo(point)) {
                min = node.p;
            }

            if (node.right != null && node.right.rect.contains(point)) {
                min = nearest(node.right, point, min);
                min = nearest(node.left, point, min);
            } else {
                min = nearest(node.left, point, min);
                min = nearest(node.right, point, min);
            }
        }
            
        return min;
        
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
