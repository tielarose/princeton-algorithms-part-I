public class ThreeWayQuicksort {
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi + 1;
        while (true)
        {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less[a[lo], a[--j]])
                if (j == lo) break;

            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo)
            return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt)
        {
            int cmp = a[i].compareTo(v)
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;   
            }

        sort (a, lo, lt - 1);
        sort (a, gt + 1, hi);
        }
    }
}