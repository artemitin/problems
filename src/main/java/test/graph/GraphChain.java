package test.graph;

import java.util.*;

public class GraphChain {
    private List<VertexChain> g;

    public GraphChain(List<VertexChain> g) {
        super();
        this.g = g;
    }

    public static void main(String[] args) {
        String[][] inputsArr = {{"eve", "eat", "ripe", "tear"}, {"hit", "hot", "dot", "dog", "cog"},
                {"aa", "ba", "ab"}, {"apple", "juice"}};
        List<List<String>> inputs = new ArrayList<>();
        for (String[] arr : inputsArr) {
            inputs.add(Arrays.asList(arr));
        }

        for (int i = 0; i < inputsArr.length; i++) {
            List<VertexChain> vertices = new ArrayList<>();
            GraphChain g = new GraphChain(vertices);
            g.createGraph(inputs.get(i));
            boolean result = g.canChainWords(inputs.get(i).size());
            System.out.println((i + 1) + "\tList of words: " + "['"
                    + String.join("', '", inputs.get(i)) + "']");
            System.out.println("\tIs word chaining possible?: " + result);
            System.out.println(
                    "----------------------------------------------------------------------------------------");
        }
    }

    // This method returns TRUE if the graph has a cycle containing
    // all the nodes, returns FALSE otherwise
    boolean canChainWordsRec(VertexChain current, VertexChain startingNode) {
        current.visited = true;

        List<VertexChain> adjacentVertices = current.adjVertices;

        // Base case
        // return TRUE if all nodes have been visited and there
        // exists an edge from the last node being visited to
        // the starting node
        if (this.allVisited()) {
            for (VertexChain adjacent : adjacentVertices) {
                if (adjacent == startingNode) {
                    return true;
                }
            }
        }

        // Recursive case
        for (VertexChain node : adjacentVertices) {
            if (!node.visited) {
                if (canChainWordsRec(node, startingNode)) {
                    return true;
                }
            }
        }
        return false;
    }


    // Challenge function
    boolean canChainWords(int listSize) {
        // Empty list and single word cannot form a chain
        if (listSize < 2) {
            return false;
        }

        if (this.g.size() > 0) {
            if (this.outEqualsIn()) {
                return this.canChainWordsRec(this.g.get(0), this.g.get(0));
            }
        }

        return false;
    }

    // This method creates a graph from a list of words. A node of
    // the graph contains a character representing the start or end
    // character of a word.
    void createGraph(List<String> wordsList) {
        for (String word : wordsList) {
            char startChar = word.charAt(0);
            char endChar = word.charAt(word.length() - 1);

            VertexChain start = vertexChainExists(startChar);

            if (start == null) {
                start = new VertexChain(startChar, false);
                this.g.add(start);
            }

            VertexChain end = vertexChainExists(endChar);

            if (end == null) {
                end = new VertexChain(endChar, false);
                this.g.add(end);
            }

            // Add an edge from start vertex to end vertex
            addEdge(start, end);
        }
    }

    // This method returns the vertex with a given value if it
    // already exists in the graph, returns NULL otherwise
    VertexChain vertexChainExists(char value) {
        for (VertexChain vertexChain : this.g) {
            if (vertexChain.value == value) {
                return vertexChain;
            }
        }
        return null;
    }

    // This method returns TRUE if all nodes of the graph have
    // been visited
    boolean allVisited() {
        for (VertexChain vertexChain : this.g) {
            if (!vertexChain.visited) {
                return false;
            }
        }
        return true;
    }

    // This method adds an edge from start vertex to end vertex by
    // adding the end vertex in the adjacency list of start vertex
    // It also adds the start vertex to the inVertices of end vertex
    void addEdge(VertexChain start, VertexChain end) {
        start.adjVertices.add(end);
        end.inVertices.add(start);
    }

    // This method returns TRUE if out degree of each vertex is equal
    // to its in degree, returns FALSE otherwise
    Boolean outEqualsIn() {
        for (VertexChain vertexChain : this.g) {
            int out = vertexChain.adjVertices.size();
            int inn = vertexChain.inVertices.size();
            if (out != inn) {
                return false;
            }
        }
        return true;
    }

    void printGraph() {
        for (VertexChain chain : this.g) {
            System.out.println(chain.value + " " + chain.visited);
            System.out.println(chain.value + " " + chain.visited);
            List<VertexChain> adj = chain.adjVertices;
            for (VertexChain vertexChain : adj) {
                System.out.println(vertexChain.value + " ");
            }
        }
    }
}
