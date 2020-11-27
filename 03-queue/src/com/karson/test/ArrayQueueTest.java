package com.karson.test;

import com.karson.ArrayQueue;
import org.junit.Test;

/**
 * @author Karson
 */
public class ArrayQueueTest {

    @Test
    public void demo1() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 15; i++) {
            queue.add(i);
        }
        while (!queue.empty()) {
            System.out.println(queue.remove());
        }
    }
}
