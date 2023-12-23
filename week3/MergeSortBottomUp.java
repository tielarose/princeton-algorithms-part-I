public class MergeSortBottomUp {
    private static Comparable aux[];

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }
    
    // private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    // {
    //     if (hi <= lo + CUTOFF  - 1){
    //         Insertion.sort(a, lo, hi);
    //         return;
    //     }
    //     int mid = lo + (hi - lo) / 2
    //     sort(a, aux, lo, mid);
    //     sort(a, aux, mid+1, hi);
    //     if (!less(a[mid+1], a[mid])) return;
    //     merge(a, aux, lo, mid, hi);
    // }

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }
}
