package test.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsTreeValidTest {

    @Test
    void test() {
        BinaryTree bt1 = new BinaryTree(List.of(100, 50, 200, 25, 75, 125, 350));
        assertTrue(IsTreeValid.isBst(bt1.root));

        BinaryTree bt2 = new BinaryTree(List.of(25, 50, 75, 100, 125, 200, 350));
        assertTrue(IsTreeValid.isBst(bt2.root));

        BinaryTree bt3 = new BinaryTree(List.of(350, 200, 125, 100, 75, 50, 25));
        assertTrue(IsTreeValid.isBst(bt3.root));

        BinaryTree bt4 = new BinaryTree(List.of(100));
        assertTrue(IsTreeValid.isBst(bt4.root));

        BinaryTree bt5 = new BinaryTree(List.of());
        assertTrue(IsTreeValid.isBst(bt5.root));
    }
}
