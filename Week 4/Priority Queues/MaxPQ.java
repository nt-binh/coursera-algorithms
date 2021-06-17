public class MaxPQ<Key extends Comparable<Key>> {
    
    private Key[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() { return N == 0; }

    public void insert(Key key) {
        pq[N++] = key;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[0];
        exch(0, N--);
        sink(0);
        pq[N+1] = null;
        return max;
    }

    private void swim(int k) {
        while (k > 0 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (true) {
            int left = 2*k + 1;
            int right = 2*k + 2;
            int largest = left;
            if (right < N && less(left, right)) largest = right;
            if (left >= N || less(largest, k)) break;
            exch(largest, k);
            k = largest;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

}
