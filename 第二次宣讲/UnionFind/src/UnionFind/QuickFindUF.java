package UnionFind;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id;
    private int count;

    private int connTimes = 0;
    private int cost = 0;
    private int total = 0;

    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    public int count() {
        return count;
    }

    public int connTimes() { return connTimes; }
    public int cost() { return cost; }
    public int total() { return total; }

    private void validate(int i) {
        int n = id.length;
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("index " + i + " is not between 0 and " + (n - 1));
        }
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        connTimes++;
        cost = 2;
        total += 2;
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pid = id[p];
        int qid = id[q];
        cost = cost + 2;
        total = total + 2;
        if (pid == qid) return;
        for (int i = 0; i < id.length; i++) {
            cost++;
            total++;
            if (id[i] == pid) {
                id[i] = qid;
                cost++;
                total++;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
        StdDraw.setXscale(0,1000);
        StdDraw.setYscale(0,1300);
        StdDraw.setPenRadius(0.005);
        while (!StdIn.isEmpty()) {
            if (uf.connTimes()>0)
            {
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.point(uf.connTimes(),uf.cost());
                //
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.point(uf.connTimes(),uf.total()/uf.connTimes());
            }
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
