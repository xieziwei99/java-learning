package 数据结构;

import java.lang.reflect.Array;

/**
 * @author xzw
 * 2021-01-25
 */
public class Stack<T> {
    private int capacity = 100;
    private T[] elements;
    private int top = 0;

    @SuppressWarnings("unchecked")
    public Stack(Class<T> clazz) {
        elements = (T[]) Array.newInstance(clazz, capacity);
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(T t) {
        if (top == capacity) {
            @SuppressWarnings("unchecked")
            T[] temp = (T[]) Array.newInstance(t.getClass(), capacity * 2);
            System.arraycopy(elements, 0, temp, 0, capacity);
            elements = temp;
            capacity *= 2;
        }
        this.elements[top] = t;
        top++;
    }

    public T peek() {
        return this.isEmpty() ? null : elements[top - 1];
    }

    public void pop() {
        if (!this.isEmpty()) {
            top--;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>(Integer.class);
        for (int i = 0; i < 100; i++) {
            integerStack.push(i);
        }
        integerStack.push(10000);
        while (!integerStack.isEmpty()) {
            System.out.println(integerStack.peek());
            integerStack.pop();
        }
    }
}
