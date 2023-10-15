package test.graph;

public class ShortestPath {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.createGraph(9,
                new int[][] {
                        {0, 1, 0, 4},          //0
                        {0, 7, 0, 8},          //0

                        {1, 7, 0, 11},       //1
                        {1, 2, 0, 8},       //1

                        {2, 8, 0, 2},    //2
                        {2, 5, 0, 4},    //2
                        {2, 3, 0, 7},    //2

                        {3, 5, 0, 14},       //3
                        {3, 4, 0, 9},       //3

                        {4, 5, 0, 10},          //4

                        {5, 6, 0, 2},    //5

                        {6, 8, 0, 6},       //6
                        {6, 7, 0, 1},       //6

                        {7, 8, 0, 7},    //7
                }
        );

        System.out.println("Initial graph");
        g.printGraph();
        g.printMatrix();

        System.out.println("Shortest path");
        g.shortestPathGraph(0).printGraph();
    }
}
