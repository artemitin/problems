package test.tree;

import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, print its perimeter nodes.
 */
public class PrintTreePerimeter {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 60, 350, 10, 70, 400));
        //100, 50, 25, 10, 70, 400, 350, 200
        displayTreePerimeter(bt1.root);
    }

    public static void displayTreePerimeter(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        StringBuilder result = new StringBuilder();
        result.append(root.data).append(", ");

        printLeftPerimeter(root.left, result);
        // Calling function to print leaf nodes
        if (root.left != null || root.right != null) {
            // We don't need to print if root is also the leaf node.
            printLeafNodes(root, result);
        }
        printRightSide(root.right, result);

// Removing trailing comma and space from our result
        if (result.length() > 2) {
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
        }
        System.out.println(result);

    }

    // Function to print left tree perimeter
    public static void printLeftPerimeter(BinaryTreeNode root, StringBuilder result) {
        while (root != null) {
            int currVal = root.data;

            // Setting root such that left boundary nodes are printed from top to bottom
            if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                // Break loop on leaf node
                break;
            }

            // Append current node to perimeter result
            result.append(currVal).append(", ");
        }
    }

    // Function to print right tree perimeter
    public static void printRightSide(BinaryTreeNode root, StringBuilder result) {
        // stack for right side values.
        Stack<Integer> rValues = new Stack<>();

        while (root != null) {
            int currVal = root.data;

            // Setting root such that right boundary nodes are stored in stack of right-side values from top to bottom
            if (root.right != null) {
                root = root.right;
            } else if (root.left != null) {
                root = root.left;
            } else {
                // Break loop on leaf node
                break;
            }
            rValues.push(currVal);
        }

        // Appending nodes in reverse order to perimeter result
        while (!rValues.isEmpty()) {
            result.append(rValues.pop()).append(", ");
        }
    }

    // Function to print leaf nodes perimeter
    public static void printLeafNodes(BinaryTreeNode root, StringBuilder result) {
        if (root != null) {
            // Recursively traversing to leaf nodes
            printLeafNodes(root.left, result);
            printLeafNodes(root.right, result);

            // Append node to result if it's a leaf node
            if (root.left == null && root.right == null) {
                result.append(root.data).append(", ");
            }
        }
    }

}
