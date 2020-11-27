package com.karson;

/**
 * 手动实现基于数组的队列
 *
 * @author Karson
 */
public class ArrayQueue<E> {
    private Object[] data;
    private int size;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public void add(E value) {
        if (size == data.length)
            resize();
        for (int i = size; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = value;
        size++;
    }

    private void resize() {
        Object[] newData = new Object[data.length << 1];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 移除队列元素
     */
    public E remove() {
        if (size == 0)
            throw new RuntimeException("当前队列为空");
        return (E) data[--size];
    }

    public E peek() {
        if (size == 0)
            throw new RuntimeException("当前队列为空");
        return (E) data[size - 1];
    }

    public boolean empty() {
        return size == 0;
    }
}
