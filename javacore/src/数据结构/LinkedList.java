package 数据结构;

/**
 * 拥有头结点的概念，头结点作为根(head)，不存储记录
 * 即链表中第一个记录，是 head.next
 *
 * @author xzw
 * 2021-03-27
 */
public class LinkedList<T> implements List<T> {
    private Object value;
    private LinkedList<T> next;
    private int size;
    private LinkedList<T> last;

    public LinkedList() {
        this.size = 0;
        this.value = null;
        this.next = null;
        this.last = this;
    }

    @Override
    public void add(T t) {
        LinkedList<T> temp = new LinkedList<>();
        temp.value = t;
        last.next = temp;
        last = temp;
        size++;
    }

    @Override
    public void add(T t, int index) {
        if (index >= size) {
            throw new IllegalArgumentException("index " + index + " 超出下标");
        }
        LinkedList<T> node = new LinkedList<>();
        node.value = t;

        LinkedList<T> pre = this;   // 插入位置的前一个
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        node.next = pre.next;
        pre.next = node;

        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("index " + index + " 超出下标");
        }
        LinkedList<T> pre = this;   // 插入位置的前一个
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre = pre.next;
        return (T) pre.value;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public void delete(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("index " + index + " 超出下标");
        }
        LinkedList<T> pre = this;   // 插入位置的前一个
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        size--;
    }

    @Override
    public void delete(T t) {
        LinkedList<T> pre = this;
        while (pre.next != null) {
            if (t.equals(pre.next.value)) {
                pre.next = pre.next.next;
                size--;
                return;
            }
            pre = pre.next;
        }
        throw new IllegalArgumentException("列表中没有值为 " + t + " 的记录");
    }

    @Override
    public void deleteAll(T t) {
        LinkedList<T> pre = this;
        while (pre.next != null) {
            if (t.equals(pre.next.value)) {
                pre.next = pre.next.next;
                size--;
            } else {
                pre = pre.next;
            }
        }
    }

    @Override
    public String toString() {
        return value + " -> " + next;
    }
}
