package test.graph;

import java.util.*;

public class VertexChain {
    public char value;
    public boolean visited;
    public List<VertexChain> adjVertices;
    public List<VertexChain> inVertices;

    public VertexChain(char value, boolean visited) {
        this.value = value;
        this.visited = visited;
        this.adjVertices = new ArrayList<>();
        this.inVertices = new ArrayList<>();
    }
}
