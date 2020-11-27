package com.karson;


import java.util.Arrays;

/**
 * 手动实现ArrayList
 *
 * @author Karson
 */
public class ArrayList<E> {
    private Object[] data;
    private int size;

    public ArrayList(int capacity) {
        if (capacity < 0)
            throw new RuntimeException("初始化容量错误");
        data = new Object[capacity];
        size = 0;
    }

    public ArrayList() {
        this(10);
    }

    /**
     * 指定位置插入元素
     */
    public void add(int index, E value) {
        if (index < 0 || index > size)
            throw new RuntimeException("添加元素下标不合法");
        if (size == data.length)
            resize();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    /**
     * 扩容，2倍
     */
    private void resize() {
        Object[] newData = new Object[data.length << 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 头插
     */
    public void addFirst(E value) {
        add(0, value);
    }

    /**
     * 尾插
     */
    public void addLast(E value) {
        add(size, value);
    }

    /**
     * 删除指定位置上的元素
     *
     * @return 被删除的元素
     */
    public E delete(int index) {
        if (index < 0 || index > size - 1)
            throw new RuntimeException("删除元素下标不合法");
        E deletedValue = (E) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return deletedValue;
    }

    /**
     * 修改指定位置上的值
     *
     * @return 指定位置上的旧值
     */
    public E modify(int index, E value) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("修改元素下标不合法");
        }
        E oldValue = (E) data[index];
        data[index] = value;
        return oldValue;
    }

    /**
     * 获取指定位置上的元素
     */
    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("查找元素下标不合法");
        }
        return (E) data[index];
    }

    /**
     * 获取ArrayList存的元素的个数
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
