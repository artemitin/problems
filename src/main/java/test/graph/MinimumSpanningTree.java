package test.graph;

import java.util.ArrayList;

/**
 * Find the minimum spanning tree (MST)
 * A spanning tree of a graph on n vertices is a subset of n-1 edges that, along with the n vertices, form a tree.
 * A minimum spanning tree (MST) of an edge-weighted graph is a spanning tree whose weight
 * (the sum of the weights of its edges) is no larger than the weight of any other spanning tree.
 * of a connected, undirected graph with weighted edges
 */
public class MinimumSpanningTree<X> {

    public static void main(String[] args) {
    }

    // This method finds the MST of a graph using Prim's Algorithm
    // Returns MST in a graph object, don't change the original graph
    static Graph findMinSpanningTree(Graph graph) {
        // TODO: Write - Your - Code
        Graph mst = new Graph(new ArrayList<>(), new ArrayList<>());

//        mst.vertices.add()
//        mst.edges.add()

        // Add first vertex to the MST
        Vertex current = graph.vertices.get(0);
        current.visited = true;
        int vertexCount = 1;

        // Iterate over all vertices because MST should contain all vertices
        while (vertexCount < graph.vertices.size()) {
            Edge smallest = null;
            for (Edge edge : graph.edges) {
                if (!edge.visited && edge.src.visited && !edge.dest.visited) {
                    if (smallest == null || edge.weight < smallest.weight) {
                        smallest = edge;
                    }
                }
            }
            vertexCount++;

            if (smallest != null) {
                smallest.visited = true;
                smallest.dest.visited = true;
                mst.edges.add(new Edge(smallest.weight, smallest.visited, smallest.src, smallest.dest));

                // populate the vertices of mst'
                if (mst.vertexExists(smallest.src.id) == null) {
                    mst.vertices.add(new Vertex(smallest.src.id, smallest.src.visited));
                }
                if (mst.vertexExists(smallest.dest.id) == null) {
                    mst.vertices.add(new Vertex(smallest.dest.id, smallest.dest.visited));
                }
            }
        }

        return mst;
    }
}
