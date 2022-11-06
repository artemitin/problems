package test.tree;

import java.util.ArrayList;
import java.util.List;

class NthHighest {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 350));
        BinaryTree.walkTreeRec(bt1.root);

        int n = 1;
        BinaryTreeNode nthHighestInBst = findNthHighestInBst(bt1.root, n);
        System.out.println(n + " highest: " + nthHighestInBst);
    }

    // Function to find the nth highest node
    public static BinaryTreeNode findNthHighestInBst(BinaryTreeNode root, int n) {
        if (n < 1) {
            return null;
        }
        List<BinaryTreeNode> nodes = new ArrayList<>();
        walkTreeRec(root, nodes);
        if (n > nodes.size()) {
            return null;
        }
        return nodes.get(n - 1);
    }

    public static void walkTreeRec(BinaryTreeNode node, List<BinaryTreeNode> nodes) {
        if (node == null) {
            return;
        }
        walkTreeRec(node.right, nodes);
        nodes.add(node);
        walkTreeRec(node.left, nodes);
    }
}
