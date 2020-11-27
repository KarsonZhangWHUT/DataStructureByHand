package com.karson.test;

import com.karson.LinkedList;
import org.junit.Test;

/**
 * @author Karson
 */
public class TestLinkedList {

    @Test
    public void demo1() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.addFirst(0);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        int size = list.size();
        System.out.println("容量：" + size);
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }
        Integer deletedValue = list.delete();
        System.out.println("被删除的元素" + deletedValue);
        int size1 = list.size();
        for (int i = 0; i < size1; i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.toString());
    }
}
