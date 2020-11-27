package com.karson.test;

import com.karson.LinkedListDeque;
import org.junit.Test;

/**
 * @author Karson
 */
public class LinkedListDequeTest {

    @Test
    public void demo() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        for (int i = 0; i < 5; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 5; i++) {
            deque.addLast(i);
        }
        while (!deque.empty()) {
            System.out.println(deque.peekFirst() + " " + deque.deleteFirst());
//            System.out.println(deque.peekLast() + " " + deque.deleteLast());
        }
    }
}
