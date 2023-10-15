package test.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    public List<Vertex> vertices = new ArrayList<>(); // vertices
    public List<Edge> edges = new ArrayList<>(); // edges
    public Map<Integer, Vertex> vertexByValue = new HashMap<>();
    private boolean directed;

    public Graph() {
    }

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        vertexByValue = vertices.stream().collect(Collectors.toMap(v -> v.id, v -> v));
    }

    public int[][] getAdjMatrix() {
        int[][] matrix = new int[vertices.size()][vertices.size()];
        for (Edge e : edges) {
            int iSrc = vertices.indexOf(e.src);
            int iDest = vertices.indexOf(e.dest);

            matrix[iSrc][iDest] = 1;

            if (!e.directed) {
                matrix[iDest][iSrc] = 1;
            }
        }
        return matrix;
    }

    // This method returns the vertex with a given id if it
    // already exists in the graph, returns NULL otherwise
    public Vertex vertexExists(int id) {
        return vertexByValue.get(id);
    }

    // This method generates the graph with v vertices
    // and e Edges
    // directed by default
    public void createGraph(int vertices, int[][] edges) {
        // create vertices
        for (int i = 0; i < vertices; i++) {
            Vertex v = new Vertex(i);
            this.vertices.add(v);
            vertexByValue.put(v.id, v);
        }

        // create edges
        for (int[] edge : edges) {
            Vertex src = vertexExists(edge[0]); // edges[i][0] is the source
            Vertex dest = vertexExists(edge[1]); // edges[i][1] is the destination
            Edge e = switch (edge.length) {
                case 2 -> new Edge(src, dest);
                // with direction
                case 3 -> new Edge(src, dest, edge[2] == 1);
                case 4 -> new Edge(src, dest, edge[2] == 1, edge[3]);
                default -> throw new IllegalStateException("Unexpected value: " + edge.length);
            };
            this.edges.add(e); // adding edges
            src.adjacent.add(dest);
            if (!e.directed) {
                dest.adjacent.add(src);
            }
        }
    }

    public void printGraph() {
        for (Edge edge : this.edges) {
//            System.out.println("\t" + edge.src.id + "--(" + edge.weight + ")--" + edge.dest.id);
            var direction = edge.directed ? "-->" : "--";
            System.out.println("\t" + edge.src.id + direction + edge.dest.id);
        }
    }

    public void printMatrix() {
        int[][] matrix = getAdjMatrix();

        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int i = 0; i < matrix.length; i++) {
            sb.append(String.format("%-2s", i));
        }
        sb.append("\n");

        for (int i = 0; i < matrix.length; i++) {
            sb.append(String.format("%-2s", i)).append("|");
            int[] a = matrix[i];
            for (int b : a) {
                sb.append(String.format("%-2s", b));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public String getGraph() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.edges.size(); i++) {
            sb.append("[").append(this.edges.get(i).src.id).append("--(").append(this.edges.get(i).weight).append(")--").append(this.edges.get(i).dest.id).append("]");
            if (i != this.edges.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (Edge edge : this.edges) {
            totalCost += edge.weight;
        }
        return totalCost;
    }

    public void union(int i1, int i2) {
        edges.add(new Edge(vertexByValue.get(i1), vertexByValue.get(i2)));
    }

    public boolean hasCycle() {
        if (vertices.isEmpty()) {
            return false;
        }

        for (Vertex vi : vertices) {
            Deque<Vertex> stack = new LinkedList<>();
            stack.add(vi);

            while (!stack.isEmpty()) {
                Vertex v = stack.removeFirst();
                v.visited = true;
                for (Vertex adj : v.adjacent) {
                    if (adj.visited) {
                        return true;
                    }
                    stack.addFirst(adj);
                }
            }

            for (Vertex x : vertices) {
                x.visited = false;
            }
        }
        return false;
    }

    public Graph shortestPathGraph(int srcId) {
        Vertex srcV = vertexByValue.get(srcId);
        if (srcV == null) {
            return null;
        }

        Graph spg = new Graph();
        int[] lastUpdatedFrom = new int[vertices.size()]; //could be a map
        Arrays.fill(lastUpdatedFrom, -1);


        int[] distance = new int[vertices.size()]; //could be a map
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[srcV.id] = 0;

        while (spg.vertices.size() != this.vertices.size()) { //todo may be queue-based
            Vertex current = getNextMinDistance(spg, vertices, distance);
            updateAdjacentDistance(distance, current, spg, lastUpdatedFrom);
            Vertex copy = current.copy(); //create copy for short path graph
            spg.addVertex(copy);
        }

        //print the distance
        for (int i = 0; i < distance.length; i++) {
            System.out.printf("distance from %s to %s is %s\n", srcId, i, distance[i]);
        }

        //createEdges
        for (int i = 0; i < lastUpdatedFrom.length; i++) {
            if (lastUpdatedFrom[i] != -1) {
                Vertex src = spg.vertexByValue.get(lastUpdatedFrom[i]);
                Vertex dest = spg.vertexByValue.get(i);
                src.adjacent.add(dest);
                Edge e = new Edge(src, dest, false);
                spg.edges.add(e);
            }
        }

        return spg;
    }

    private Vertex getNextMinDistance(Graph shortestPath, List<Vertex> vertices, int[] distance) {
        return vertices.stream()
                .filter(v -> !shortestPath.vertexByValue.containsKey(v.id)) //not visited
                .min(Comparator.comparingInt(v -> distance[v.id]))
                .get();
    }

    private void updateAdjacentDistance(int[] distance, Vertex current, Graph spg, int[] lastUpdatedFrom) {
        for (Vertex adjV : current.adjacent) {
            if (!spg.vertexByValue.containsKey(adjV.id)) { //TODO need a queue to eliminate this condition
                int distanceToAdjV = distance[current.id] + findEdge(current, adjV).weight;
                if (distanceToAdjV < distance[adjV.id]) {
                    distance[adjV.id] = distanceToAdjV;
                    lastUpdatedFrom[adjV.id] = current.id;
                }
            }
        }
    }

    private Edge findEdge(Vertex src, Vertex dest) {
        //todo could be too slow, need O(1) time
        for (Edge e : edges) {
            if (e.src.id == src.id && e.dest.id == dest.id) {
                return e;
            } else if (!directed && e.src.id == dest.id && e.dest.id == src.id) {
                return e;
            }
        }
        return null;
    }

    private void addVertex(Vertex v) {
        vertices.add(v);
        vertexByValue.put(v.id, v);
    }
}