package UnionFind;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;

    private int connTimes = 0;
    private int cost = 0;
    private int total = 0;

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        StdDraw.setXscale(0,1000);
        StdDraw.setYscale(0,1000);
        StdDraw.setPenRadius(0.005);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connTimes() > 0) {
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.point(uf.connTimes(), uf.cost());

                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.point(uf.connTimes(), uf.total() / uf.connTimes());
            }
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

    public int count() {
        return count;
    }

    public int connTimes() {
        return connTimes;
    }
    public int cost() {
        return cost;
    }
    public int total() {
        return total;
    }

    private int root(int i) {
        validate(i);
        cost++;
        total++;
//        while (i != id[i])
//            i = id[i];
//         return i;

        while (i != id[i]) {
            cost++;
            total++;
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    private void validate(int i) {
        int n = id.length;
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("index " + i + " is not between 0 and " + (n - 1));
        }
    }

    private boolean connected(int p, int q) {
        connTimes++;
        cost = 0;
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        cost = cost + 6;
        total = total + 6;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}
