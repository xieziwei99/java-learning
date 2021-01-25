package 数据结构;

import java.util.ArrayList;

/**
 * @author xzw
 * 2021-01-25
 */
public class Stack<T> {
    private final ArrayList<T> element = new ArrayList<>();

    public boolean isEmpty() {
        return this.element.isEmpty();
    }

    public void push(T t) {
        this.element.add(t);
    }

    public T peek() {
        return this.isEmpty() ? null : this.element.get(element.size() - 1);
    }

    public void pop() {
        if (!this.isEmpty()) {
            this.element.remove(element.size() - 1);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            integerStack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(integerStack.peek());
            integerStack.pop();
        }
    }
}
