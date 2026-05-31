import java.util.*;

public class CO3_DSURollback {

    static class DSURollback {

        int[] parent, rank;
        Deque<int[]> history = new ArrayDeque<>();

        DSURollback(int n) {

            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {

            while (parent[x] != x)
                x = parent[x];

            return x;
        }

        boolean union(int a, int b) {

            int ra = find(a);
            int rb = find(b);

            if (ra == rb) {
                history.push(new int[]{-1,-1,-1});
                return false;
            }

            if (rank[ra] < rank[rb]) {
                int temp = ra;
                ra = rb;
                rb = temp;
            }

            history.push(
                new int[]{rb, ra, rank[ra]}
            );

            parent[rb] = ra;

            if (rank[ra] == rank[rb])
                rank[ra]++;

            return true;
        }

        void rollback() {

            int[] h = history.pop();

            if (h[0] == -1)
                return;

            parent[h[0]] = h[0];
            rank[h[1]] = h[2];
        }
    }

    public static void main(String[] args) {

        String[] airports = {
            "BLR","HYD","MAA","COK",
            "BOM","DEL","CCU","AMD"
        };

        DSURollback dsu =
            new DSURollback(8);

        dsu.union(0,1);
        dsu.union(1,4);
        dsu.union(4,5);

        if (dsu.find(0) == dsu.find(5))
            System.out.println(
                airports[0] +
                " is connected to " +
                airports[5]);

        dsu.rollback();

        System.out.println(
            "Last union rolled back.");
    }
}