package com.karson.test;

import com.karson.List;
import org.junit.Test;

/**
 * @author Karson
 */
public class ListTest {

    @Test
    public void demo() {
        List<Integer> list = new List<Integer>();

        for (int i = 0; i < 10; i++) {
            list.addFirst(i);
        }
        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }
        while (!list.empty()) {
            System.out.println(list.delete(list.size() - 1));
        }
    }
}
