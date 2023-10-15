package test.graph;

import java.util.Objects;

public class Edge {
    public boolean visited;
    public Vertex src;
    public Vertex dest;
    //src->dest: 1
    //src<-->dest: 0
    //src<-dest: -1
    public boolean directed = true;
    public int weight = 0;

    public Edge(Vertex src, Vertex dest) {
        this.src = src;
        this.dest = dest;
    }

    public Edge(Vertex src, Vertex dest, boolean directed) {
        this.src = src;
        this.dest = dest;
        this.directed = directed;
    }

    public Edge(Vertex src, Vertex dest, boolean directed, int weight) {
        this.src = src;
        this.dest = dest;
        this.directed = directed;
        this.weight = weight;
    }

    //legacy
    public Edge(int weight, boolean visited, Vertex src, Vertex dest) {
        this.weight = weight;
        this.visited = visited;
        this.src = src;
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src.id +
                ", dest=" + dest.id +
                '}';
    }
}
