package com.karson.test;

import com.karson.LinkedListQueue;
import org.junit.Test;

/**
 * @author Karson
 */
public class LinkedListQueueTest {

    @Test
    public void demo1() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        while (!queue.empty()) {

            System.out.println(queue.peek() + " " + queue.remove());
        }
    }
}
