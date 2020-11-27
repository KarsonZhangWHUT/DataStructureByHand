package com.karson.test;

import com.karson.ArrayList;
import org.junit.Test;

/**
 * @author Karson
 */
public class TestArrayList {
    @Test
    public void demo01() {
        ArrayList<Integer> list = new ArrayList<>();
//        list.add(9, 10);
        int size = list.size();
        System.out.println("初始化大小：" + size);
        list.addFirst(1);
        int size1 = list.size();
        System.out.println("添加1个元素后大小：" + size1);
        Integer integer = list.get(0);
        System.out.println("位置1上的元素：" + integer);
        for (int i = 2; i <= 10; i++) {
            list.addLast(i);
        }
        int size2 = list.size();
        System.out.println("添加10个元素后大小：" + size2);
        for (int i = 0; i < 10; i++) {
            System.out.print(list.get(i) + "");
        }
        System.out.println();
        list.addFirst(0);
        list.add(5, 55);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + "");
        }

    }

    @Test
    public void testAddFirst() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.addFirst(0);
        arrayList.addFirst(1);
        arrayList.addFirst(2);
        arrayList.addFirst(3);
        arrayList.addFirst(4);
        arrayList.addFirst(5);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        arrayList.add(6, 10);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println(arrayList.toString());
    }


}
