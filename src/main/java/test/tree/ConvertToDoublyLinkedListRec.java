package test.tree;

import java.util.*;

/**
 * Convert a given binary tree to a doubly-linked list,
 * such that the order of the doubly-linked list is the same as the in-order traversal of the binary tree.
 *
 * After conversion, the left pointer of the node should point to the previous node in the doubly linked list,
 * and the right pointer should point to the next node in the doubly linked list.
 * The head node of the linked list must be the first node in the in-order traversal of the original binary tree.
 */
public class ConvertToDoublyLinkedListRec {

    // Initializing the first and the last nodes
    public static BinaryTreeNode first = null;
    public static BinaryTreeNode last = null;

    // Recursive function to convert
    public static void convertToLinkedListRec(BinaryTreeNode currNode) {
        // Return if the current node is null
        if (currNode != null) {
            // Performing in-order tree traversal
            convertToLinkedListRec(currNode.left);

            // Processing the current node
            if (last != null) {
                // Connecting the last and current nodes
                last.right = currNode;
                currNode.left = last;
            } else {
                // Initializing the first node
                first = currNode;
            }

            // Updating the current node
            last = currNode;
            convertToLinkedListRec(currNode.right);
        }
    }

    public static BinaryTreeNode convertToLinkedList(BinaryTreeNode root) {
        if (root == null) {
            // Return null if the root doesn't exist
            return null;
        } else {
            first = null;
            last = null;
            convertToLinkedListRec(root);

            // Closing the linked list
            last.right = null;
            first.left = null;
            return first;
        }
    }

    public static void main(String[] argv) {
        // Creating a binary search tree
        List<Integer> input1 = new ArrayList<Integer>();
        input1.add(100);
        input1.add(50);
        input1.add(200);
        input1.add(25);
        input1.add(75);
        input1.add(125);
        input1.add(350);
        BinaryTree tree1 = new BinaryTree(input1);

        // Creating a binary tree with wrong node in left subtree
        BinaryTree tree2 = new BinaryTree(100);
        tree2.insert(50);
        tree2.insert(200);
        tree2.insert(25);
        // Add a node at an incorrect position
        tree2.insertBT(110);
        tree2.insert(125);
        tree2.insert(350);

        // Creating a binary tree with wrong node in right subtree
        BinaryTree tree3 = new BinaryTree(100);
        tree3.insert(50);
        tree3.insert(200);
        tree3.insert(25);
        tree3.insert(75);
        // Add a node at an incorrect position
        tree3.insertBT(90);
        tree3.insert(350);

        // Creating a right degenerate binary search tree
        List<Integer> input4 = new ArrayList<Integer>();
        input4.add(100);
        input4.add(50);
        input4.add(200);
        input4.add(25);
        input4.add(75);
        input4.add(125);
        input4.add(350);
        Collections.sort(input4);
        BinaryTree tree4 = new BinaryTree(input4);

        // Creating a left degenerate binary search tree
        List<Integer> input5 = new ArrayList<Integer>();
        input5.add(100);
        input5.add(50);
        input5.add(200);
        input5.add(25);
        input5.add(75);
        input5.add(125);
        input5.add(350);
        Collections.sort(input5, Collections.reverseOrder());
        BinaryTree tree5 = new BinaryTree(input5);

        // Creating a single node binary search tree
        BinaryTree tree6 = new BinaryTree(100);

        // Defining test cases
        BinaryTreeNode[] testCaseRoots =
                {tree1.root, tree2.root, tree3.root, tree4.root, tree5.root, tree6.root, null};


        // BinaryTreeNode[] testCaseRoots = {tree1.root};

//        for (int i = 0; i < testCaseRoots.length; i++) {
//            if (i > 0) {
//                System.out.print("\n");
//            }
//            System.out.println((i + 1) + ".\tBinary tree:");
//            TreePrint.displayTree(testCaseRoots[i]);
//            // System.out.println(TreePrint.getLvlOrder(testCaseRoots[i]));
//            System.out.print("\n\tDoubly Linked List:\n\t");
//            ConvertToDoublyLinkedListRec.displayList(convertToLinkedList(testCaseRoots[i]));
//            System.out.print(
//                    "----------------------------------------------------------------------------------------------------\n");
//        }
    }
}
