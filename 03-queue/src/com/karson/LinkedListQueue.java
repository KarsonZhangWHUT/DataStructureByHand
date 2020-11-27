package com.karson;

/**
 * 手动实现基于链表的队列
 *
 * @author Karson
 */
public class LinkedListQueue<E> {
    private static class Node<E> {
        Node<E> pre;
        E value;
        Node<E> next;

        Node(Node<E> pre, E value, Node<E> next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> first;
    private Node<E> last;

    public LinkedListQueue() {
    }

    public void add(E value) {
        Node<E> newNode = new Node<>(null, value, first);
        if (first == null) {
            last = newNode;
        } else {
            first.pre = newNode;
        }
        first = newNode;
    }

    public E remove() {
        if (last == null)
            throw new RuntimeException("当前队列为空");
        E removedValue = last.value;
        if (last.pre == null) {
            last = null;
            first = null;
        } else {
            Node<E> preLast = last.pre;
            preLast.next = null;
            last.pre = null;
            last = preLast;
        }
        return removedValue;
    }

    public E peek() {
        if (last == null)
            throw new RuntimeException("当前队列为空");
        return last.value;
    }

    public boolean empty() {
        return last == null;
    }
}
