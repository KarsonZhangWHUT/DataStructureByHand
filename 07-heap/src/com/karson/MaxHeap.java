package com.karson;

import java.util.Arrays;
import java.util.List;

/**
 * @author Karson
 * 基于动态数组实现最大堆
 * 用数组实现完全二叉树
 */
public class MaxHeap {
    private Integer[] data;
    private int size;

    public MaxHeap(int capacity) {
        this.size = 0;
    }

    public MaxHeap() {
        this.data = new Integer[10];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    /**
     * 查找指定位置的父索引
     */
    public int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("root没有夫索引");
        if (index > size - 1)
            throw new ArrayIndexOutOfBoundsException("index越界");
        return (index - 1) / 2;
    }

    /**
     * 查找指定索引节点的左孩子节点的索引
     */
    public int leftChild(int index) {
        if (index > size)
            throw new ArrayIndexOutOfBoundsException(index + "越界");
        int leftChildIndex = index << 2 + 1;
        if (leftChildIndex > size - 1)
            throw new IllegalArgumentException(index + "位置的节点是叶子节点");
        return leftChildIndex;
    }

    /**
     * 查找指定索引节点的右孩子节点的索引
     */
    public int rightChild(int index) {
        if (index > size)
            throw new ArrayIndexOutOfBoundsException(index + "越界");

        int rightChildIndex = (index + 1) << 1;
        if (rightChildIndex > size - 1)
            throw new IllegalArgumentException(index + "位置的节点没有右孩子");
        return rightChildIndex;
    }

    public void add(Integer value) {
        if (size == data.length)
            resize();
        data[size++] = value;
        shiftUp(size - 1);
    }

    /**
     * 上浮节点
     */
    private void shiftUp(int index) {
        while (index > 0 && data[parent(index)].compareTo(data[index]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * 交换两个节点
     */
    private void swap(int i, int j) {
        Integer temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private void resize() {
        Integer[] newData = new Integer[size << 1];
        if (size >= 0) System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public Integer getMax() {
        if (size == 0)
            throw new IllegalStateException("当前heap为空");
        return data[0];
    }

    /**
     * 取出最大值
     */
    public Integer extractMax() {
        Integer result = getMax();
        swap(0, size - 1);
        size--;
        shiftDown(0);
        return result;
    }

    /**
     * 下沉节点
     */
    private void shiftDown(int index) {
        while (index >= 0 && leftChild(index) < size - 1) {
            //判断index的左右节点哪个大
            int successor = leftChild(index);
            if (successor + 1 < size - 1 && data[successor + 1].compareTo(data[successor]) > 0)
                successor++;
            //比较大小，看是否还需要下沉
            if (data[index].compareTo(data[successor]) >= 0)
                break;
            swap(index, successor);
            index = successor;
        }
    }

    /**
     * 层次遍历
     *
     * @return
     */
    public List<Integer> levelOrder() {
        return Arrays.asList(data);
    }

}
