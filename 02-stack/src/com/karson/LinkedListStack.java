package com.karson;

/**
 * 手动实现基于双向链表的栈
 *
 * @author Karson
 */
public class LinkedListStack<E> {
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

    private Node<E> cur;

    public LinkedListStack() {
    }

    public void push(E value) {
        Node<E> newNode = new Node<E>(cur, value, null);
        if (cur != null)
            cur.next = newNode;
        cur = newNode;
    }

    public E pop() {
        if (cur == null)
            throw new RuntimeException("该Stack为空");
        E popValue = cur.value;
        if (cur.pre == null) {
            cur = null;
        } else {
            Node<E> old = cur;
            cur = cur.pre;
            cur.next = null;
            old.pre = null;
        }
        return popValue;
    }

    public boolean empty() {
        return cur == null;
    }

    public E peek() {
        if (cur == null)
            throw new RuntimeException("该Stack为空");
        return cur.value;
    }
}
