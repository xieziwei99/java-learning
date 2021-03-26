import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import 数据结构.ArrayList;
import 数据结构.List;

import java.lang.reflect.Field;

/**
 * @author xzw
 * 2021-03-26
 */
public class ArrayListTest {

    @Test
    public void testAddAndGet() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(i + 1);
        }
        list.add(100, 5);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 100, 6, 7, 8, 9, ]", list.toString());

        Assertions.assertEquals(4, list.get(3));
        Assertions.assertEquals(10, list.length());
    }

    @Test
    public void testDelete(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        list.delete(6);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 8, 9, 10, ]", list.toString());

        list.delete(Integer.valueOf(3));
        Assertions.assertEquals("[1, 2, 4, 5, 6, 8, 9, 10, ]", list.toString());

        list.add(5);
        Assertions.assertEquals("[1, 2, 4, 5, 6, 8, 9, 10, 5, ]", list.toString());

        list.deleteAll(5);
        Assertions.assertEquals("[1, 2, 4, 6, 8, 9, 10, ]", list.toString());
    }

    @Test
    public void test扩容() throws NoSuchFieldException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        Assertions.assertEquals(10, list.length());

        list.add(11);
        Assertions.assertEquals(11, list.length());

        @SuppressWarnings("rawtypes")
        Class<? extends List> clazz = list.getClass();
        Field capacity = clazz.getDeclaredField("capacity");
        capacity.setAccessible(true);
        Assertions.assertEquals(15, capacity.getInt(list));

        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ]", list.toString());
    }

    @Test
    public void testIllegalArgument() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i + 1);
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.add(6, 10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.delete(6));
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.delete(Integer.valueOf(100)));
    }
}
