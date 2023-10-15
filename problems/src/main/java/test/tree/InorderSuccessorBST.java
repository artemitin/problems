package test.tree;

import java.util.List;

/**
 * Write a method to find the in-order successor of a BST node with a given value.
 * Return -1 if the node with the given value does not exist.
 * The in-order successor of a node in a BST is the node with the smallest key greater than the key of the current node.
 * This is the same as the next node in an in-order traversal of the BST.
 */
public class InorderSuccessorBST {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 125, 350));
        BinaryTreeNode inorderSuccessor = findInorderSuccessor(bt1.root, 100);
        System.out.println(inorderSuccessor);
    }

    public static BinaryTreeNode findInorderSuccessor(BinaryTreeNode root, int nodeValue) {
        BinaryTreeNode targetNode = getTargetNode(root, nodeValue);
        BinaryTreeNode result = targetNode.right;

        // find the minimum value node in sub-tree
        while (result.left != null) {
            result = result.left;
        }
        return result;
    }

    private static BinaryTreeNode getTargetNode(BinaryTreeNode root, int nodeValue) {
        BinaryTreeNode target = root;
        while (target != null) {
            if (target.data == nodeValue) {
                return target;
            }
            if (nodeValue > target.data) {
                target = target.right;
            } else {
                target = target.left;
            }
        }
        return new BinaryTreeNode(-1);
    }

    // Function to find the minimum value node in sub-tree
    static BinaryTreeNode treeMin(BinaryTreeNode root) {
        // Traversing to the left-most child node
        while (root.left != null) {
            root = root.left;
        }

        // Returns the left-most node of sub-tree
        return root;
    }

    // Function to find the in-order successor
    static BinaryTreeNode findInorderSuccessor2(BinaryTreeNode root, int nodeValue) {
        // Null check
        if (root == null) {
            return null;
        }

        // Initializing variable that will store any potential in-order successor
        // node in the parent chain
        BinaryTreeNode successor = null;

        // Loop to traverse to the node in question and find its in-order successor
        while (root != null) {
            // Move root pointer to right child if the nodeValue
            // is more than the current node data
            if (root.data < nodeValue) {
                root = root.right;
            } else if (root.data > nodeValue) {
                // If the nodeValue is less than the current root
                // node then point successor to the current root
                successor = root;
                // and point root pointer to left child
                root = root.left;
            } else {
                // Find min value node of
                // right child's subtree if it exists.
                if (root.right != null) {
                    successor = treeMin(root.right);
                }
                break;
            }

            // If node is not found
            if (root == null) {
                return new BinaryTreeNode(-1);
            }
        }
        return successor;
    }
}
