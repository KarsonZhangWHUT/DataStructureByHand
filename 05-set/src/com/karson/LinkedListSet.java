package com.karson;

/**
 * 利用双向链表实现集合
 *
 * @author Karson
 */
public class LinkedListSet<E extends Comparable<E>> {
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

    private int size;
    private Node<E> first;
    private Node<E> last;

    public LinkedListSet() {
        this.first = this.last = null;
        this.size = 0;
    }

    /**
     * 向集合中添加元素
     *
     * @return true：添加成功  false：添加失败（集合中已经存在这个值）
     */
    public boolean add(E value) {
        if (contains(value))
            return false;
        else {
            Node<E> newNode = new Node<>(last, value, null);
            if (last == null)
                first = newNode;
            else
                last.next = newNode;
            last = newNode;
            size++;
            return true;
        }
    }

    public boolean contains(E value) {
        Node<E> cur = first;
        while (cur != null) {
            if (value == null && cur.value == null) {
                return true;
            } else if (value.equals(cur.value)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean delete(E value) {
        Node<E> cur = first;
        while (cur != null) {
            if (value == null && cur.value == null) {
                break;
            } else if (value.equals(cur.value)) {
                break;
            }
            cur = cur.next;
        }
        if (cur == null)
            return false;
        else {
            if (cur.pre == null) {
                if (last == first)
                    last = null;
                first = cur.next;
                cur.next = null;
            } else if (cur.next == null) {
                if (first == last)
                    first = null;
                last = cur.pre;
                cur.pre = null;
            } else {
                cur.pre.next = cur.next;
                cur.next.pre = cur.pre;
                cur.next = cur.pre = null;
            }
            size--;
            return true;
        }
    }

    public boolean empty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        if (index < 0 || index > size - 1)
            throw new RuntimeException("查询下标不合法");
        Node<E> cur = first;
        int temp = index;
        while (temp-- > 0)
            cur = cur.next;
        return cur.value;
    }
}
