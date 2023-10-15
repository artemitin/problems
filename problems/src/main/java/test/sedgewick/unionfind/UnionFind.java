package test.sedgewick.unionfind;

import test.graph.Graph;

public class UnionFind {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.createGraph(8,
                new int[][]{
                        new int[]{1, 4},
                        new int[]{4, 5},
                        new int[]{2, 3},
                        new int[]{2, 6},
                        new int[]{3, 6},
                        new int[]{3, 7}
                }
        );

        g.printGraph();
        g.printMatrix();

        g.union(2, 5);

        g.printMatrix();

//        boolean same = isConnectedTogether(2, 5);
    }

    private static boolean isConnectedTogether(int i1, int i2) {
        return false;
    }
}
