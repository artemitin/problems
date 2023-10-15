package test.tree;

import java.util.*;

/**
 * Given a binary tree, at each level in the hierarchy, connect each node to the one on its right.
 */
public class ConnectSiblings {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 60, 350, 10, 70, 400));
        //100, 50, 25, 10, 70, 400, 350, 200
        populateNextPointers(bt1.root);
    }

    // Function to populate same level pointers
    public static void populateNextPointers(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        // Initializing the dummy node
        BinaryTreeNode dummyNode = new BinaryTreeNode(-1);
        Queue<BinaryTreeNode> level = new LinkedList<>();

        level.add(root);
        level.add(dummyNode);

        BinaryTreeNode prev = null;

        while(!level.isEmpty()) {
            BinaryTreeNode head = level.remove();

            if (head == dummyNode) {
                if (prev != null) {
                    prev.next = null;
                    prev = null;
                }
                if (!level.isEmpty()) {
                    level.add(dummyNode);
                }
            } else {
                if (prev != null) {
                    prev.next = head;
                }
                if (head.left != null) {
                    level.add(head.left);
                }
                if (head.right != null) {
                    level.add(head.right);
                }
                prev = head;
            }
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
