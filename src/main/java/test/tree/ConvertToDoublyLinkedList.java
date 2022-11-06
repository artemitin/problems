package test.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Convert a given binary tree to a doubly-linked list,
 * such that the order of the doubly-linked list is the same as the in-order traversal of the binary tree.
 *
 * After conversion, the left pointer of the node should point to the previous node in the doubly linked list,
 * and the right pointer should point to the next node in the doubly linked list.
 * The head node of the linked list must be the first node in the in-order traversal of the original binary tree.
 */
public class ConvertToDoublyLinkedList {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 35));
        convertToLinkedList(bt1.root);
    }

    public static BinaryTreeNode convertToLinkedList(BinaryTreeNode node) {
        if (node == null) {
            System.out.println("null");
            return null;
        }
        Deque<BinaryTreeNode> stack = new LinkedList<>();
        BinaryTreeNode current = node;
        BinaryTreeNode head = null;

        BinaryTreeNode prev = null;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();

                current.left = prev;
                if (prev != null) {
                    prev.right = current;
                } else {
                    head = current;
                }
                prev = current;

                current = current.right;
            }
        }

        return head;
    }
}
