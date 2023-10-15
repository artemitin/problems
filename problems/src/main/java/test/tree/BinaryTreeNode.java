package test.tree;

public class BinaryTreeNode {
    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    // below data members used only for some of a problems
    public BinaryTreeNode next;
    public BinaryTreeNode parent;
    public int count;

    public BinaryTreeNode(int d) {
        data = d;
        left = null;
        right = null;
        next = null;
        parent = null;
        count = 0;
    }

    @Override
    public String toString() {
        return data + "";
    }
}