package com.karson.test;

import com.karson.ArrayDeque;
import org.junit.Test;

/**
 * @author Karson
 */
public class ArrayDequeTest {

    @Test
    public void demo1() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 6; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 7; i++) {
            deque.addLast(i);
        }

        while (!deque.empty()) {
            System.out.println(deque.peekFirst() + " " + deque.deleteFirst());

//            System.out.println(deque.peekLast() + " " + deque.deleteLast());
        }
    }
}
