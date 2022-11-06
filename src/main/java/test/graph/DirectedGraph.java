package test.graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<X> {
    List<Node<X>> nodes;

    DirectedGraph() {
        nodes = new ArrayList<>();
    }

    void addVertex(X data) {
        Node<X> objNode = new Node<>(data);
        this.nodes.add(objNode);
    }

    Node<X> findVertexByData(X data) {
        for (Node<X> node : this.nodes) {
            if (node.data == data) {
                return node;
            }
        }
        return null;
    }

    int count() {
        return this.nodes.size();
    }

    void addEdge(X startNode, X endNode) {
        Node<X> start = this.findVertexByData(startNode);
        Node<X> end = this.findVertexByData(endNode);
        if (start != null && end != null)
            start.neighbors.add(end);
        else
            System.out.println("Node not found\n");
    }

    void printGraph() {
        for (Node<X> node : this.nodes) {
            System.out.print(node.data + " ->{ ");
            for (Node<X> n : node.neighbors) {
                System.out.print(n.data + " ");
            }
            System.out.print("}\n");
        }
    }

    void addVertexInNodes(Node<X> node) {
        this.nodes.add(node);
    }

    void deleteEdge(X node, X neighbor) {
        for (Node<X> v : this.nodes) {
            if (v.data == node) {
                for (Node<X> n : v.neighbors) {
                    if (n.data == neighbor) {
                        v.neighbors.remove(n);
                        break;
                    }
                }
                break;
            }
        }
    }
}