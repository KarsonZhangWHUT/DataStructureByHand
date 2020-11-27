package com.karson.test;

import com.karson.BinarySearchTree;
import org.junit.Test;

import java.util.List;

/**
 * @author Karson
 */
public class BinarySearchTreeTest {

    @Test
    public void demo() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(5);

        tree.add(5);
        tree.add(5);
        tree.add(9);
        tree.add(6);
        tree.add(7);
        tree.add(3);
        tree.add(0);
        tree.add(10);
        tree.add(2);
        tree.add(4);

        List<Integer> midOrder = tree.midOrder();
        midOrder.forEach(System.out::print);
        System.out.println();
        System.out.println(tree.size());
        tree.delete(6);
        tree.delete(1);
        System.out.println("-------------------------------------------");
        midOrder = tree.midOrder();
        midOrder.forEach(System.out::print);
        System.out.println();
        System.out.println(tree.size());
        System.out.println("-------------------------------------------");

        System.out.println(tree.contains(6));
        System.out.println(tree.contains(7));
        System.out.println("-------------------------------------------");
        System.out.println(tree.depth());

    }
}
