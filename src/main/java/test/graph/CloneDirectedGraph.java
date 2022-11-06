package test.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the root node of a directed graph, clone this graph by creating its deep copy,
 * such that the cloned graph has the same vertices and edges as the original graph.
 */
public class CloneDirectedGraph<X> {

    public static void main(String[] args) {
        DirectedGraph<Integer> dg = new DirectedGraph<>();
        dg.addVertex(0);
        dg.addVertex(1);
        dg.addVertex(2);
        dg.addVertex(3);
        dg.addVertex(4);
        dg.addEdge(0, 2);
        dg.addEdge(0, 3);
        dg.addEdge(0, 4);
        dg.addEdge(1, 2);
        dg.addEdge(2, 0);
        dg.addEdge(3, 2);
        dg.addEdge(4, 1);
        dg.addEdge(4, 3);
        dg.addEdge(4, 0);

        dg.printGraph();
        DirectedGraph<Integer> cloned = new CloneDirectedGraph<Integer>().clone(dg);
        cloned.printGraph();
    }

    public DirectedGraph<X> clone(DirectedGraph<X> graph) {
        // Hashmap to keep record of visited nodes
        Map<Node<X>, Node<X>> nodesCompleted = new HashMap<>();

        // Creating new graph
        DirectedGraph<X> cloneGraph = new DirectedGraph<>();

        // Returning an empty if the original is also empty
        if (graph.nodes.isEmpty()) {
            return cloneGraph;
        }

        // cloneRec function call
        // Passing first node as root node
        cloneRec(graph.nodes.get(0), cloneGraph, nodesCompleted);

        // Return deep copied graph
        return cloneGraph;
    }

    private Node<X> cloneRec(Node<X> root, DirectedGraph<X> clonedGraph, Map<Node<X>, Node<X>> nodesCompleted) {
        // Base case when there is no node
        if (root == null) {
            return null;
        }

        // Creating new vertex/node
        Node<X> newNode = new Node<>(root.data);

        // Using hashmap to keep track of visited nodes
        nodesCompleted.put(root, newNode);

        // Adding new vertex in the clonedGraph
        clonedGraph.addVertexInNodes(newNode);

        // Iterate over each neighbor of the current vertex/node
        for (Node<X> p : root.neighbors) {
            Node<X> x = nodesCompleted.get(p);
            if (x == null) {
                // If node is not visited call recursive function to create vertex/node
                newNode.neighbors.add(cloneRec(p, clonedGraph, nodesCompleted));
            } else {
                // If node is visited just add it to the neighbors of current vertex/node
                newNode.neighbors.add(x);
            }
        }
        return newNode;
    }
}
