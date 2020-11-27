package com.karson;

/**
 * 手动实现基于动态数组的栈
 *
 * @author Karson
 */
//pop  push  peek empty
public class ArrayStack<E> {
    private Object[] data;
    private int size;

    public ArrayStack() {
        this(10);
    }

    public ArrayStack(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public void push(E value) {
        if (size == data.length) {
            resize();
        }
        data[size++] = value;
    }

    private void resize() {
        Object[] newData = new Object[data.length << 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public E pop() {
        if (size == 0)
            throw new RuntimeException("当前Stack为空");
        return (E) data[--size];
    }

    public E peek() {
        if (size == 0)
            throw new RuntimeException("当前Stack为空");
        return (E) data[size - 1];
    }

    public boolean empty() {
        return size == 0;
    }

}
