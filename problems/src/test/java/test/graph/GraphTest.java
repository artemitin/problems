package test.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {
    @Test
    public void testHasCycleNoCycle() {
        // Test 1: Create a graph with no cycles
        Graph graph1 = new Graph();
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}};
        graph1.createGraph(4, edges1);
        assertFalse(graph1.hasCycle(), "Graph 1 should not have a cycle");
    }

    @Test
    public void testHasCycleWithCycle() {
        // Test 2: Create a graph with a cycle
        Graph graph2 = new Graph();
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        graph2.createGraph(4, edges2);
        assertTrue(graph2.hasCycle(), "Graph should have a cycle");
    }

    @Test
    public void testHasCycleDisconnectedGraph() {
        // Test 3: Create a disconnected graph
        Graph graph3 = new Graph();
        int[][] edges3 = {{0, 1}, {2, 3}};
        graph3.createGraph(4, edges3);
        assertFalse(graph3.hasCycle(), "Graph should not have a cycle");
    }

    @Test
    public void testHasCycleDisconnectedLoopedGraph() {
        // Test 4: Create a disconnected looped graph
        Graph graph31 = new Graph();
        int[][] edges31 = {{0, 1}, {2, 3}, {3, 4}, {4, 2}};
        graph31.createGraph(5, edges31);
        assertTrue(graph31.hasCycle(), "Graph should have a cycle");
    }

    @Test
    public void testHasCycleSelfLoops() {
        // Test 5: Create a graph with self-loops
        Graph graph4 = new Graph();
        int[][] edges4 = {{0, 0}, {1, 1}, {2, 2}};
        graph4.createGraph(3, edges4);
        assertTrue(graph4.hasCycle(), "Graph should have a cycle (self-loops)");
    }

    @Test
    public void testHasCycleEmptyGraph() {
        // Test 6: Create an empty graph
        Graph graph5 = new Graph();
        assertFalse(graph5.hasCycle(), "Graph (empty) should not have a cycle");
    }

    @Test
    public void hasNoCycleTreeType() {
        Graph graph = new Graph();
        int[][] edges = {{3, 1}, {3, 4}, {1, 0}, {1, 2}};
        graph.createGraph(5, edges);
        assertFalse(graph.hasCycle(), "Graph1 should not have a cycle");
    }

}
