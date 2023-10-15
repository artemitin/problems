package test.graph;

import java.util.ArrayList;
import java.util.List;

public class Node<X> {
    X data;
    List<Node<X>> neighbors;

    Node(X data) {
        this.data = data;
        this.neighbors = new ArrayList<>();
    }
}