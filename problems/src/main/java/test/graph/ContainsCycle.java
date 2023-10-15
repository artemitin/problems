package test.graph;

public class ContainsCycle {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.createGraph(4,
                new int[][]{
                        new int[]{0, 1, 1},
                        new int[]{0, 2, 1},
                        new int[]{1, 2, 1},
                        new int[]{2, 3, 1},
                        new int[]{3, 1, 1},
                }
        );

        g.printGraph();
        g.printMatrix();

        System.out.println("Has cycle? " + g.hasCycle());
    }
}
