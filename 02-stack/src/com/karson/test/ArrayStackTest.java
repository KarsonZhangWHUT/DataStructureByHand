package com.karson.test;

import com.karson.ArrayStack;
import org.junit.Test;

/**
 * @author Karson
 */
public class ArrayStackTest {

    @Test
    public void demo1() {
        ArrayStack<Integer> stack = new ArrayStack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        System.out.println(stack.empty());
        for (int i = 0; i < 15; i++) {
            stack.push(i);
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
