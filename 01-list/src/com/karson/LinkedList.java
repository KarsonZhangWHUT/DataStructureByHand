package com.karson;

/**
 * 手写LinkedList
 *
 * @author Karson
 */
public class LinkedList<E> {
    /**
     * LinkedList的节点
     */
    private class Node<E> {
        E value;
        Node<E> pre, next;

        public Node(Node<E> pre, E value, Node<E> next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedList() {
    }

    /**
     * 插入元素，默认尾插
     */
    public void add(E value) {
        addLast(value);
    }

    /**
     * 尾插
     */
    public void addLast(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        last = newNode;
        size++;
    }

    /**
     * 头插
     */
    public void addFirst(E value) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, value, f);
        if (f == null)
            last = newNode;
        else
            f.pre = newNode;
        first = newNode;
        size++;
    }

    /**
     * 删除元素，默认尾删
     */
    public E delete() {
        return deleteLast();
    }

    /**
     * 尾删
     */
    public E deleteLast() {
        if (last == null)
            throw new RuntimeException("当前LinkedList为空，无法删除元素");
        E deletedValue = last.value;
        Node<E> beforeLast = last.pre;
        beforeLast.next = null;
        last.pre = null;
        last = beforeLast;
        size--;
        return deletedValue;
    }

    /**
     * 头删
     */
    public E deleteFirst() {
        if (first == null)
            throw new RuntimeException("当前LinkedList为空，无法删除元素");
        E deletedValue = first.value;
        Node<E> nextFirst = first.next;
        nextFirst.pre = null;
        first.next = null;
        first = nextFirst;
        size--;
        return deletedValue;
    }

    /**
     * 删除指定元素
     *
     * @return true删除成功  false没有该元素
     */
    public boolean deleteEle(E e) {
        if (e == null) {
            Node<E> cur = first;
            while (cur != null) {
                if (cur.value == null) {
                    del(cur);
                    return true;
                }
                cur = cur.next;
            }
        } else {
            Node<E> cur = first;
            while (cur != null) {
                if (cur.value.equals(e)) {
                    del(cur);
                    return true;
                }
                cur = cur.next;
            }
        }
        return false;
    }

    private E del(Node<E> cur) {
        E deletedValue = cur.value;
        if (cur.pre == null) {
            first = cur.next;
            first.pre = null;
            cur.next = null;
        } else if (cur.next == null) {
            last = cur.pre;
            last.next = null;
            cur.pre = null;
        } else {
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            cur.next = null;
            cur.pre = null;
        }
        size--;
        return deletedValue;
    }

    /**
     * 删除指定位置的元素
     */
    public E deleteEle(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("超出LinkedList下标范围");
        }
        Node<E> cur = first;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return del(cur);
    }

    /**
     * 修改指定位置上的元素
     */
    public E modify(int index, E value) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("超出LinkedList下标范围");
        }
        Node<E> cur = first;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        E oldValue = cur.value;
        cur.value = value;
        return oldValue;
    }

    /**
     * 查询指定位置上的元素
     */
    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("超出LinkedList下标范围");
        }
        Node<E> cur = first;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    /**
     * LinkedList的大小
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedList{data=[");
        Node<E> cur = first;
        for (int i = 0; i < size; i++) {
            sb.append(cur.value).append(",");
            cur = cur.next;
        }
        sb.append("]").append(",size=").append(size);
        return sb.toString();

    }
}
