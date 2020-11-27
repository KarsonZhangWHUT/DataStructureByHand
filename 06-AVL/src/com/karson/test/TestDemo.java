package com.karson.test;

import com.karson.AVLTree;
import org.junit.Test;

import java.util.List;

/**
 * @author Karson
 */
public class TestDemo {

    @Test
    public void demo1() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(56);
        tree.add(77);
        tree.add(962);
        tree.add(54);
        tree.add(423);
        tree.add(89);
        tree.add(732);
        tree.add(23);
        tree.add(14);
        tree.add(72);
        tree.add(92);
        tree.add(92);
        tree.add(92);
        tree.add(92);
        tree.add(92);
        List<Integer> list = tree.midOrder();
        list.forEach(System.out::println);


        tree.remove(92);
        tree.remove(77);
        tree.remove(423);
        tree.remove(14);
        List<Integer> list2 = tree.midOrder();
        list2.forEach(System.out::println);
    }
}
