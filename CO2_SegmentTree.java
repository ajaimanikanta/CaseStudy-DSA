public class CO2_SegmentTree {

    static class SegTreeMax {

        static final long NEG_INF = Long.MIN_VALUE;

        long[] tree;
        int n;

        SegTreeMax(int n) {
            this.n = n;
            tree = new long[4 * n];
        }

        void build(int node, int lo, int hi, int[] arr) {

            if (lo == hi) {
                tree[node] = arr[lo];
                return;
            }

            int mid = (lo + hi) / 2;

            build(2 * node, lo, mid, arr);
            build(2 * node + 1, mid + 1, hi, arr);

            tree[node] = Math.max(tree[2 * node],
                                  tree[2 * node + 1]);
        }

        long queryMax(int node, int lo, int hi,
                      int l, int r) {

            if (r < lo || hi < l)
                return NEG_INF;

            if (l <= lo && hi <= r)
                return tree[node];

            int mid = (lo + hi) / 2;

            long left =
                queryMax(2 * node, lo, mid, l, r);

            long right =
                queryMax(2 * node + 1,
                         mid + 1,
                         hi,
                         l,
                         r);

            return Math.max(left, right);
        }
    }

    public static void main(String[] args) {

        int[] arr = {71,73,78,75,82,79,77,80};

        SegTreeMax st = new SegTreeMax(arr.length);

        st.build(1,0,arr.length-1,arr);

        System.out.println(
            "Range Max [3..7] = "
            + st.queryMax(1,0,7,2,6));
    }
}