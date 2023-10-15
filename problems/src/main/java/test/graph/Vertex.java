package test.graph;

import java.util.*;

public class Vertex {
    public int id;
    public boolean visited;
    public Set<Vertex> adjacent = new HashSet<>();

    public Vertex(int id) {
        this.id = id;
        this.visited = false;
    }

    public Vertex(int id, boolean visited) {
        this.id = id;
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Vertex copy() {
        return new Vertex(id);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", visited=" + visited +
                '}';
    }
}
