import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {
    
    private final Point[] points;
    private final int numberOfSegments;
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        int len = points.length;
        this.points = new Point[len];
        for (int i = 0; i < len; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            for (int j = 0; j < i; j ++){
                if (this.points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            }
            this.points[i] = points[i];
        }
        List<LineSegment> lineSegments = new LinkedList<>();
        Arrays.sort(points);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int l = k + 1; l < len; l++) {
                        double slopeIJ = points[i].slopeTo(points[j]);
                        double slopeIK = points[i].slopeTo(points[k]);
                        double slopeIL = points[i].slopeTo(points[l]);
                        if (slopeIJ == slopeIK && slopeIK == slopeIL){
                            lineSegments.add(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
        }
        this.numberOfSegments = lineSegments.size();
        this.segments = lineSegments.toArray(new LineSegment[numberOfSegments]);
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
