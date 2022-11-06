package test.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, write an iterative algorithm for the in-order traversal of a binary tree.
 * The algorithm should print out the value of each node such that it conforms to the in-order traversal of the input binary tree.
 * The function is not expected to return a value, only to print it out on the console.
 */
public class IterativeInorderTraversal {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 35));
        iterativeInorder(bt1.root);
    }

    public static void iterativeInorder(BinaryTreeNode node) {
        if (node == null) {
            System.out.println("null");
            return;
        }
        Deque<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode current = node;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                System.out.println(current.data);
                current = current.right;
            }
        }
    }
}
