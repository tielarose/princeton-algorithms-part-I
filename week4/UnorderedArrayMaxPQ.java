package week4;
public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // pq[i] = ith element on pq
    private int n; // number of elements on pq
    
    // no generic array creation
    // in this version, user indicates capacity
    public UnorderedARrayMaxPQ(int capacity){
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }
        
    public void insert(Key x) {
        pq[n++] = x;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < n; i++)
            if (less(max, i))
                max = i;
        exch(max, n - 1);

        return pq[--n];
    }

    // helper functions
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

}