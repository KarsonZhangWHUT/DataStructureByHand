package com.karson.test;

import com.karson.LinkedListSet;
import org.junit.Test;

/**
 * @author Karson
 */
public class LinkedListSetTest {

    @Test
    public void demo() {
        LinkedListSet<Integer> set = new LinkedListSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }
        System.out.println("添加" + 5 + "的结果：" + set.add(5));

        System.out.println("删除" + 5 + "的结果：" + set.delete(5));
        System.out.println("删除" + 5 + "的结果：" + set.delete(5));
        for (int i = 0; i < set.size(); i++) {
            System.out.println(set.get(i));
        }
    }
}
