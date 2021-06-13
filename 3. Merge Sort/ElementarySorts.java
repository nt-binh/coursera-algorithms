import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;

public class ElementarySorts {
    
    /**
     * Question 1
     * Intersection of two sets.
     * Given two arrays a[] and b[], each containing N distinct 2D points in the plane,
     * design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].
     */

    class Point implements Comparable<Point> {

        private int x;
        private int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point that) {
            if (this.x < that.x) return -1;
            if (this.x > that.x) return 1;
            if (this.y < that.y) return -1;
            if (this.y > that.y) return 1;
            return 0;
        }

    }

    public int countIntersections(Point[] a, Point[] b) {
        Insertion.sort(a);
        Insertion.sort(b);
        int count = 0;                                      // number of intersections
        int i = 0, j = 0;                                   // used for incrementing in point a and point b
        while (i < a.length && j < b.length) {
            int compare = a[i].compareTo(b[j]);
            if (compare == 0) {
                i++;
                j++;
                count++;
            } else if (compare < 0) {
                i++;
            } else {
                j++;
            }
        }
        return count;
    }

    /**     
     * Question 2
     * Permutation. 
     * Given two integer arrays of size n, 
     * design a subquadratic algorithm to determine whether one is a permutation of the other. 
     * That is, do they contain exactly the same entries but, possibly, in a different order.
    */

    public boolean isPermutation(Comparable[] a, Comparable[] b) {
        Merge.sort(a);
        Merge.sort(b);
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    /**
     * Question 3
     * Dutch national flag. Given an array of N buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:
     * swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
     * color(i): color of pebble in bucket i.
     * The performance requirements are as follows:
     * At most N calls to color().
     * At most N calls to swap().
     * Constant extra space.
     */

    private enum Pebble {
        red,white,blue;
    }

    class Buckets {

        private Pebble[] pebbles;
        
        private Pebble color(int i) {
            return Pebble[i];
        }

        private int compare(Pebble other) {
            Pebble white = Pebble.white;
            return other.ordinal() - white.ordinal();
        }

        private void swap(int i, int j) {
            Pebble temp = pebbles[i];
            pebbles[i] = pebbles[j];
            pebbles[j] = temp;
        }

        public void sort() {
            assert pebbles.length > 0;
            int red = 0, blue = pebbles.length - 1;
            int i = 0;
            while (i <= blue) {
                int compare = compare(color(i));
                if (compare < 0) {
                    swap(red++, i++);
                } else if (compare > 0) {
                    swap(blue--, i++);
                } else {
                    i++;
                }
            }
        }

    }

}
