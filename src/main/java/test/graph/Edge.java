package test.graph;

public class Edge {
    public int weight;
    public boolean visited;
    public Vertex src;
    public Vertex dest;

    public Edge(int weight, boolean visited, Vertex src, Vertex dest) {
        this.weight = weight;
        this.visited = visited;
        this.src = src;
        this.dest = dest;
    }
}
