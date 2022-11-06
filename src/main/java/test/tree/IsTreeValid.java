package test.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, figure out whether it’s a valid binary search tree.
 *
 * In a BST, each node’s key value is smaller than the key value of the right subtree nodes and
 * greater than the key values of the left subtree nodes.
 */
public class IsTreeValid {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 125, 350));
        isBst(bt1.root);
    }

    public static boolean isBst(BinaryTreeNode node) {
        if (node == null) {
            return true;
        }
        Deque<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode current = node;

        BinaryTreeNode prev = null;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();

                if (prev != null && prev.data > current.data) {
                    return false;
                }
                prev = current;

                current = current.right;
            }
        }
        return true;
    }
}
