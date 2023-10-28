/*
costs
initialize: O(n)
union: O(log n)
find:O(log n)

yay! this one is minimally acceptable
*/

public class QuickUnionWeightedUF {
    private int[] id;
    private int[] sz;
    // initialize the empty id array of size N
    // set the id of each element to itself, size of each tree rooted at that element to 1
    // N array accesses
    public QuickUnionWeightedUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
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
    
    // check the size of the trees p and q are on;
    // connect the root of the smaller tree to the root of the larger tree
    // this avoids tall trees
    // update the size of the larger tree
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

        }
    }
