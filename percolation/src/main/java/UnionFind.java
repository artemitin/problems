public class UnionFind {
    private final int[] path;
    private final int[] weights;

    public UnionFind(int[] path) {
        this.path = path;
        this.weights = new int[path.length];
    }

    public void union(int i1, int i2) {
        if (i1 == i2)
            return;

        int r1 = root(i1);
        int r2 = root(i2);

        if (r1 == r2) {
            return;
        }

        //2200
        if (weights[r1] > weights[r2]) {
            path[r2] = r1;
            weights[r1] += weights[r2];
        } else {
            path[r1] = r2;
            weights[r2] += weights[r1];
        }
    }

    public boolean connected(int i1, int i2) {
        return root(i1) == root(i2);
    }

    public int root(int i) {
        while (i != path[i]) {
            i = path[i];
        }
        return i;
    }
}
