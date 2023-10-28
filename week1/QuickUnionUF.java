/*
costs
initialize: O(n)
union: O(n)
find:O(n)

problem: find is too expensive; 
trees can get really tall
*/

public class QuickUnionUF {
    private int[] id;

    // initialize the empty id array of size N
    // set the id of each element to itself
    // N array accesses
    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    
    // find the root of an element by tracing it's parents' parents until you reach a root
    // depth of i array accesses
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    // p and q are connected if they have the same root
    // depth of p and q array accesses
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    
    // change the root of p to point to the root of q
    // depth of p and q array accesses
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
        }
    }
