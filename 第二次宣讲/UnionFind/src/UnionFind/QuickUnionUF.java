package UnionFind;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
    private int[] id;
    private int count;

    private int connTimes = 0;
    private int cost = 0;
    private int total = 0;

    public QuickUnionUF(int N) {
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(n);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);
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
        while (i != id[i]) {
            i = id[i];
            cost++;
            total++;
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

    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
        count--;
        cost++;
        total++;
    }
}
