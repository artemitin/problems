package test.tree;

import java.util.ArrayList;
import java.util.List;

class Mirror {

    public static void main(String[] args) {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 350));
        BinaryTree.walkTreeRec(bt1.root);

        mirrorBinaryTree(bt1.root);

    }


    public static void mirrorBinaryTree(BinaryTreeNode node) {
        if (node == null) {
            return;
        }

        BinaryTreeNode rightTmp = node.right;

        mirrorBinaryTree(rightTmp);

        BinaryTreeNode leftTmp = node.left;
        node.left = node.right;
        node.right = leftTmp;

        mirrorBinaryTree(leftTmp);
    }
}
