package com.karson.test;

import com.karson.MaxHeap;
import org.junit.Test;

import java.util.List;

/**
 * @author Karson
 */
public class TestDemo {

    @Test
    public void demo1() {
        MaxHeap maxHeap = new MaxHeap();

        for (int i = 0; i < 10; i++) {
            maxHeap.add(i);
        }

        List<Integer> list = maxHeap.levelOrder();
        list.forEach(System.out::println);

    }
}
