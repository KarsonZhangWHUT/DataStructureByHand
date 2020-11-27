package com.karson;

/**
 * @author Karson
 */
public class LinkedListDeque<E> {
    private static class Node<E> {
        Node<E> pre;
        E value;
        Node<E> next;

        public Node(Node<E> pre, E value, Node<E> next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    public LinkedListDeque() {
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(null, value, first);
        if (first == null)
            last = newNode;
        else
            first.pre = newNode;
        first = newNode;
        size++;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<>(last, value, null);
        if (last == null)
            first = newNode;
        else
            last.next = newNode;
        last = newNode;
        size++;
    }

    public E deleteFirst() {
        if (first == null)
            throw new RuntimeException("当前队列为空");
        E deletedValue = first.value;
        Node<E> nextFirst = first.next;
        if (nextFirst == null) {
            first = null;
            last = null;
        } else {
            first.next = null;
            nextFirst.pre = null;
            first = nextFirst;
        }
        size--;
        return deletedValue;
    }

    public E deleteLast() {
        if (last == null)
            throw new RuntimeException("当前队列为空");
        E deletedValue = last.value;
        Node<E> preLast = last.pre;
        if (preLast == null) {
            last = null;
            first = null;
        } else {
            last.pre = null;
            preLast.next = null;
            last = preLast;
        }
        size--;
        return deletedValue;
    }

    public E peekLast() {
        if (last == null)
            throw new RuntimeException("当前队列为空");
        return last.value;
    }

    public E peekFirst() {
        if (first == null)
            throw new RuntimeException("当前队列为空");
        return first.value;
    }

    public boolean empty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
