package test.graph;

import java.util.*;

public class Graph {
    public List<Vertex> vertices; // vertices
    public List<Edge> edges; // edges

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    // This method returns the vertex with a given id if it
    // already exists in the graph, returns NULL otherwise
    Vertex vertexExists(int id) {
        for (Vertex vertex : vertices) {
            if (vertex.id == id) {
                return vertex;
            }
        }
        return null;
    }

    // This method generates the graph with v vertices
    // and e Edges
    void generateGraph(int vertices, List<ArrayList<Integer>> edges) {
        // create vertices
        for (int i = 0; i < vertices; i++) {
            Vertex v = new Vertex(i + 1, false);
            this.vertices.add(v);
        }

        // create edges
        for (ArrayList<Integer> edge : edges) {
            Vertex src = vertexExists(edge.get(0)); // edges[i][0] is source
            Vertex dest = vertexExists(edge.get(1)); // edges[i][1] is destination
            Edge e = new Edge(edge.get(2), false, src, dest); // edges[i][2] is weight
            this.edges.add(e); // adding edges
        }
    }

    void printGraph() {
        for (Edge edge : this.edges) {
            System.out.println("\t" + edge.src.id + "--(" + edge.weight + ")--"
                    + edge.dest.id);
        }
    }

    String getGraph() {
        String res = "";
        for (int i = 0; i < this.edges.size(); i++) {
            if (i == this.edges.size() - 1) {
                res += "[" + this.edges.get(i).src.id + "--(" + this.edges.get(i).weight + ")--" + this.edges.get(i).dest.id + "]";
            } else {
                res += "[" + this.edges.get(i).src.id + "--(" + this.edges.get(i).weight + ")--" + this.edges.get(i).dest.id + "],";
            }
        }
        return res;
    }

    int getTotalCost() {
        int totalCost = 0;
        for (Edge edge : this.edges) {
            totalCost += edge.weight;
        }
        return totalCost;
    }
}