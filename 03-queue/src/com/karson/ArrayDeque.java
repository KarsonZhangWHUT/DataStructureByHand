package com.karson;

/**
 * 基于动态数组实现双端队列
 *
 * @author Karson
 */
public class ArrayDeque<E> {
    private Object[] data;
    private int size;

    public ArrayDeque() {
        this(10);
    }

    public ArrayDeque(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public void addFirst(E value) {
        if (size == data.length)
            resize();
        for (int i = size; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = value;
        size++;
    }

    public void addLast(E value) {
        if (size == data.length)
            resize();
        data[size++] = value;
    }

    private void resize() {
        Object[] newData = new Object[data.length << 1];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public E deleteFirst() {
        if (size == 0)
            throw new RuntimeException("当前队列为空");
        E deletedValue = (E) data[0];
        for (int i = 0; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return deletedValue;
    }

    public E deleteLast() {
        if (size == 0)
            throw new RuntimeException("当前队列为空");
        E deletedValue = (E) data[--size];
        return deletedValue;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public E peekLast() {
        if (size == 0)
            throw new RuntimeException("当前队列为空");
        return (E) data[size - 1];
    }

    public E peekFirst() {
        if (size == 0)
            throw new RuntimeException("当前队列为空");
        return (E) data[0];
    }

    public boolean contains(E value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null)
                    return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(data[i]))
                    return true;
            }
        }
        return false;
    }
}
