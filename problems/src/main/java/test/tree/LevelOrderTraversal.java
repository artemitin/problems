package test.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, display the values of its nodes while performing a level-order traversal.
 * We must print node values for all levels separated by the specified character
 */
public class LevelOrderTraversal {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 350));
        levelOrderTraversal(bt1.root);
    }

    public static void levelOrderTraversal(BinaryTreeNode root) {
        if (root == null) {
            System.out.print("null");
            return;
        }

        // Initializing the dummy node
        BinaryTreeNode dummyNode = new BinaryTreeNode(-1);
        Queue<BinaryTreeNode> level = new LinkedList<>();

        level.add(root);
        level.add(dummyNode);

        while(!level.isEmpty()) {
            // Dequeuing and printing the first element of queue
            BinaryTreeNode head = level.remove();
            System.out.print(" " + head.data);

            if (head.left != null) {
                level.add(head.left);
            }
            if (head.right != null) {
                level.add(head.right);
            }

            // When the dummyNode comes next in line, we print a new line and dequeue
            // it from the queue
            if (level.peek() == dummyNode) {
                level.remove();
                // If the queue is still not empty we add back the dummy node
                if (!level.isEmpty()) {
                    System.out.print(" : ");
                    level.add(dummyNode);
                }
            } else {
                System.out.print(", ");
            }
        }

    }
}
