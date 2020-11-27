package com.karson.test;

import com.karson.BinarySearchTreeSet;
import org.junit.Test;

import java.util.List;

/**
 * @author Karson
 */
public class BinarySearchTreeTest {

    @Test
    public void demo() {
        BinarySearchTreeSet<Integer> set = new BinarySearchTreeSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }
        List<Integer> order = set.midOrder();
        order.forEach(System.out::print);
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("添加5：" + set.add(5));
        System.out.println("删除5：" + set.delete(5));
        order = set.midOrder();
        order.forEach(System.out::print);
    }
}
