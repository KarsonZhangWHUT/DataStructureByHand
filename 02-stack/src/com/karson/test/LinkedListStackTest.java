package com.karson.test;

import com.karson.LinkedListStack;
import org.junit.Test;

/**
 * @author Karson
 */
public class LinkedListStackTest {

    @Test
    public void demo1() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("当前栈顶元素：" + stack.peek());
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
