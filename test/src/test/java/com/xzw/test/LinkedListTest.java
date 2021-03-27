package com.xzw.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import 数据结构.ArrayList;
import 数据结构.LinkedList;
import 数据结构.List;

/**
 * @author xzw
 * 2021-03-27
 */
public class LinkedListTest {

    @Test
    public void testAdd() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        Assertions.assertEquals("null -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> null", list.toString());
        Assertions.assertEquals(10, list.length());
    }

    @Test
    public void testAddAtIndex() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        Assertions.assertEquals(10, list.length());
        list.add(100, 5);
        Assertions.assertEquals("null -> 1 -> 2 -> 3 -> 4 -> 5 -> 100 -> 6 -> 7 -> 8 -> 9 -> 10 -> null",
                list.toString());
        Assertions.assertEquals(11, list.length());
    }

    @Test
    public void testIllegalArgument() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i + 1);
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.get(5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.add(6, 10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.delete(6));
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.delete(Integer.valueOf(100)));
    }

    @Test
    public void testGet() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        Assertions.assertEquals(6, list.get(5));
    }

    @Test
    public void testDelete() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        list.delete(5);
        Assertions.assertEquals("null -> 1 -> 2 -> 3 -> 4 -> 5 -> 7 -> 8 -> 9 -> 10 -> null", list.toString());
        Assertions.assertEquals(9, list.length());
    }

    @Test
    public void testDeleteFirstValue() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        list.delete(Integer.valueOf(5));
        Assertions.assertEquals("null -> 1 -> 2 -> 3 -> 4 -> 6 -> 7 -> 8 -> 9 -> 10 -> null", list.toString());
        Assertions.assertEquals(9, list.length());
    }

    @Test
    public void testDeleteAll() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        list.add(3);
        list.add(5);
        list.add(3);
        list.add(100);
        list.deleteAll(3);
        Assertions.assertEquals("null -> 1 -> 2 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 5 -> 100 -> null",
                list.toString());
        Assertions.assertEquals(11, list.length());
    }
}
