import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    
    private final Point[] points;
    private final int numberOfSegments;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        int numberOfPoints = points.length;
        this.points = new Point[numberOfPoints];
        Point[] tempPoints = new Point[numberOfPoints];
        List<LineSegment> lineSegments = new LinkedList<LineSegment>();
        List<Point> skipPoints = new LinkedList<>();

        for (int i = 0; i < numberOfPoints; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            for (int j = 0; j < i; j ++){
                if (this.points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            }
            this.points[i] = points[i];
            tempPoints[i] = points[i];
        }
        for (int i = 0; i < numberOfPoints; i++) {
            if (skipPoints(skipPoints, points[i])) continue;
            
            Point currPoint = points[i];
            Arrays.sort(tempPoints, 0, numberOfPoints, currPoint.slopeOrder());
            int segmentIndex = 1;
            int collinearPointsSize = 1;
            boolean startSegment = false;
            for (int j = segmentIndex; j < numberOfPoints - 1; j++) {
                if (currPoint.slopeTo(tempPoints[j]) == currPoint.slopeTo(tempPoints[j+1])) {
                    collinearPointsSize++;
                    if (!startSegment) {
                        startSegment = true;
                        segmentIndex = j;
                    }
                }else{
                    if (startSegment) {
                        break;
                    }
                }
            }

            if (collinearPointsSize >= 3) {
                Point[] collinearPointSet = new Point[collinearPointsSize + 1];
                collinearPointSet[0] = currPoint;
                for (int j = 0; j < collinearPointsSize; j++) {
                    collinearPointSet[j+1] = tempPoints[j + segmentIndex];
                }

                Arrays.sort(collinearPointSet, 0, collinearPointsSize + 1);
                lineSegments.add(new LineSegment(collinearPointSet[0], collinearPointSet[collinearPointsSize]));
                for (int j = 0; j < collinearPointsSize + 1; j++) {
                    skipPoints.add(collinearPointSet[j]);
                }
            }
        }
        this.numberOfSegments = lineSegments.size();
        this.segments = lineSegments.toArray(new LineSegment[this.numberOfSegments]);
    }

    private static boolean skipPoints(List<Point> points, Point p) {
        return points
        .stream()
        .filter(point -> point.compareTo(p) == 0)
        .findFirst()
        .isPresent();
    }

    public int numberOfSegments() {
        return this.numberOfSegments;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, numberOfSegments());    
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
