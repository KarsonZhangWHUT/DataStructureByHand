package com.karson;


/**
 * @author Karson
 */

public class List<E> {
    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> dummyNode = new Node<>(null, null);
    private int size;

    public List() {
    }

    public void addFirst(E value) {
        add(0, value);
    }


    public void addLast(E value) {
        add(size, value);
    }

    public void add(int index, E value) {
        if (index < 0 || index > size)
            throw new RuntimeException("插入位置不合法");
        Node<E> cur = dummyNode;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        Node<E> newNode = new Node<>(value, null);
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    public E deleteFirst() {
        return delete(0);
    }

    public E deleteLast() {
        return delete(size - 1);
    }

    public E delete(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("删除位置不合法");
        Node<E> cur = dummyNode;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        E deletedValue = cur.next.value;
        cur.next = cur.next.next;
        size--;
        return deletedValue;
    }

    public E modify(int index, E value) {
        if (index < 0 || index >= size)
            throw new RuntimeException("修改位置不合法");
        Node<E> cur = dummyNode;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        E oldValue = cur.next.value;
        cur.next.value = value;
        return oldValue;
    }

    public boolean empty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
