/*
costs
initialize: O(n)
union: O(n)
find:O(1)

problem: union is too expensive; 
takes O(n^2) time to process n union commands on n objects
*/


public class QuickFindUF {
    private int[] id;

    // initialize the empty id array of size N
    // set the id of each element to itself
    public QuickFindUF(int N) {
        id = new int[N]
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    // p and q are connected if they have the same value in the id array
    public boolean connected(int p, int q) {
        return id[p] == id[q]
    }
    
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        // change all entries with pid to qid
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
