package test.tree;

import java.util.ArrayList;
import java.util.List;

class NthHighestBetter {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 350));
        BinaryTree.walkTreeRec(bt1.root);

        int n = 1;
        BinaryTreeNode nthHighestInBst = findNthHighestInBst(bt1.root, n);
        System.out.println(n + " highest: " + nthHighestInBst);
    }

    // Function to find the nth highest node
    // Declaring a global variable called currentCount
    public static int currentCount = 0;

    // Recursive function call to find nth highest node
    public static BinaryTreeNode findNthHighestInBstRec(BinaryTreeNode node, int n) {
        // Base case: we return null if the current node is null
        if (node == null) {
            return null;
        }

        // Checking for nth highest node in right subtree
        BinaryTreeNode result = findNthHighestInBstRec(node.right, n);

        // If we find the nth highest node in the right subtree we return it
        if (result != null) {
            return result;
        }

        // Update the current count of nodes visited in reverse in-order
        currentCount++;

        // When the current count equals n, then we have found the nth highest
        // value node and return it
        if (n == currentCount) {
            return node;
        }

        // Checking for nth highest node in left subtree
        result = findNthHighestInBstRec(node.left, n);

        // If we find the nth highest node in the left subtree we return it
        if (result != null) {
            return result;
        }

        // Return null if the nth highest value node is not found
        return null;
    }

    // Function to find the nth highest node
    public static BinaryTreeNode findNthHighestInBst(BinaryTreeNode root, int n) {
        // Reset the current count to zero
        currentCount = 0;

        // Return null if the tree itself is null
        if (root == null) {
            return null;
        }

        // Making the first recursive call
        return findNthHighestInBstRec(root, n);
    }
}
