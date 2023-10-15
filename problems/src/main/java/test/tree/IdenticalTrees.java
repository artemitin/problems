package test.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the roots of two binary trees, determine if the trees are identical or not.
 */
public class IdenticalTrees {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(25, 50, 100, 125, 200, 350));
        BinaryTree bt2 = new BinaryTree(List.of(25, 50, 100, 125, 200, 350));

        boolean result = areIdentical(bt1.root, bt2.root);
        System.out.println(result);
    }

    public static boolean areIdentical(BinaryTreeNode root1, BinaryTreeNode root2) {
        return walkTree(root1).equals(walkTree(root2));
    }

    private static List<Integer> walkTree(BinaryTreeNode node) {
        List<Integer> content = new LinkedList<>();
        if (node == null) {
            return content;
        }
        Deque<BinaryTreeNode> stack = new LinkedList<>();
        stack.push(node);

        while(!stack.isEmpty()) {
            BinaryTreeNode current = stack.pop();
            content.add(current.data);

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return content;
    }

    // Function to check if the two trees are identical
    public static boolean areIdenticalRecursive(BinaryTreeNode tree1, BinaryTreeNode tree2) {
        // Returns true if both trees have null as tree (first base case)
        if (tree1 == null && tree2 == null) {
            return true;
        }

        // Function returns false if one of the trees here is null (second base case)
        if (tree1 != null && tree2 != null) {
            // Returns true if both nodes have the same left sub-tree, right sub-tree
            // and value
            return (areIdenticalRecursive(tree1.left, tree2.left) && areIdenticalRecursive(tree1.right, tree2.right)
                    && (tree1.data == tree2.data));
        }

        // Returns false if neither of the above cases is satisfied
        return false;
    }
}
