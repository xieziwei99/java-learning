package com.xzw.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import 数据结构.BinaryTree;

/**
 * @author xzw
 * 2021-03-28
 */
public class BinaryTreeTest {

    @Test
    public void testCheckSum() {
        /*
        		89
            36
                78
        12
            23
         */
        BinaryTree<Integer> tree1 = new BinaryTree<>(12);
        tree1.setLeft(new BinaryTree<>(23));
        tree1.setRight(new BinaryTree<>(36).setLeft(new BinaryTree<Integer>().setValue(78)).setRight(new BinaryTree<>(89)));

        Assertions.assertTrue(tree1.checkSum(35));
        Assertions.assertTrue(tree1.checkSum(126));
        Assertions.assertTrue(tree1.checkSum(137));
        Assertions.assertFalse(tree1.checkSum(111));
    }

    @Test
    public void testAcquirePathWithSpecifySum() {
        /*
        		89
            36
                78
        12
            23
         */
        BinaryTree<Integer> tree1 = new BinaryTree<>(12);
        tree1.setLeft(new BinaryTree<>(23));
        tree1.setRight(new BinaryTree<>(36).setLeft(new BinaryTree<Integer>().setValue(78)).setRight(new BinaryTree<>(89)));

        Assertions.assertEquals("[12, 23, ]", tree1.acquirePathWithSpecifySum(35).toString());
        Assertions.assertEquals("[12, 36, 78, ]", tree1.acquirePathWithSpecifySum(126).toString());
        Assertions.assertEquals("[12, 36, 89, ]", tree1.acquirePathWithSpecifySum(137).toString());
        Assertions.assertEquals("[]", tree1.acquirePathWithSpecifySum(111).toString());
    }
}
