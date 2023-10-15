package test.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, at each level in the hierarchy, connect each node to the one on its right.
 */
public class ConnectSiblingsNoQueue {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 60, 350, 10, 70, 400));
        //100, 50, 25, 10, 70, 400, 350, 200
        populateNextPointers(bt1.root);
    }

    // Helper function to connect all children nodes at the next level
    private static BinaryTreeNode connectNextLevel(BinaryTreeNode head) {
        // Declaring the necessary pointers
        BinaryTreeNode current = head;
        BinaryTreeNode nextLevelHead = null;
        BinaryTreeNode prev = null;

        while (current != null) {
            if (current.left != null && current.right != null) {
                // If both current node children are not null
                // then connect them with each other and previous
                // nodes in the same level.
                if (nextLevelHead == null) {
                    nextLevelHead = current.left;
                }
                current.left.next = current.right;

                if (prev != null) {
                    prev.next = current.left;
                }
                prev = current.right;
            } else if (current.left != null) {
                // If only the left child node is not null
                // then only connect it with previous same level nodes
                if (nextLevelHead == null) {
                    nextLevelHead = current.left;
                }
                if (prev != null) {
                    prev.next = current.left;
                }
                prev = current.left;
            } else if (current.right != null) {
                // If only the right child node children is not null
                // then only connect it with previous same level nodes
                if (nextLevelHead == null) {
                    nextLevelHead = current.right;
                }
                if (prev != null) {
                    prev.next = current.right;
                }
                prev = current.right;
            }

            // Update current pointer
            current = current.next;
        }

        // Pointing the last node (right-most node) of the next level
        // to null
        if (prev != null) {
            prev.next = null;
        }

        // Return the head node (left-most node) of the next level
        return nextLevelHead;
    }

    // Function to populate same level pointers
    public static void populateNextPointers(BinaryTreeNode node) {
        if (node != null) {
            node.next = null;
            // While
            do {
                node = connectNextLevel(node);
            } while (node != null);
        }
    }

    // Do not modify the code below
    // Function to find the given node and return its next node
    public static BinaryTreeNode getNextNode(BinaryTreeNode node, int nodeData) {
        // Performing Binary Search
        while (node != null && nodeData != node.data) {
            if (nodeData < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        // If node is not found return -1 else return its next node
        if (node == null) {
            return new BinaryTreeNode(-1);
        } else {
            return node.next;
        }
    }
}
