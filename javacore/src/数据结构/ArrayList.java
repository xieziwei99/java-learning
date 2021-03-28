package 数据结构;

/**
 * @author xzw
 * 2021-03-23
 */
public class ArrayList<T> implements List<T> {

    private Object[] elements;

    /**
     * size 的大小等于 ArrayList 中已存入的元素个数
     * 同时指代下一个 add 的位置
     */
    private int size;

    private int capacity;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }

    public ArrayList(ArrayList<T> sample) {
        if (sample != null) {
            this.size = sample.size;
            this.capacity = sample.capacity;
            this.elements = new Object[capacity];
            System.arraycopy(sample.elements, 0, this.elements, 0, size);
        } else {
            throw new IllegalArgumentException("参数 sample 不应该为空");
        }
    }

    @Override
    public void add(T t) {
        ensureCapacity(size + 1);
        elements[size] = t;
        size++;
    }

    @Override
    public void add(T t, int index) {
        if (index >= size) {
            throw new IllegalArgumentException("index " + index + " 超出数组下标");
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = t;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("index " + index + " 超出下标");
        }
        return (T) elements[index];
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public void delete(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("index " + index + " 超出数组下标");
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    @Override
    public void delete(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                return;
            }
        }
        throw new IllegalArgumentException("列表中没有值为 " + t + " 的记录");
    }

    @Override
    public void deleteAll(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                i--;    // 第 i 号被第 i+1 号填充，于是下一次循环还是从第 i 处开始
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            ret.append(elements[i]).append(", ");
        }
        ret.append("]");
        return ret.toString();
    }

    private void ensureCapacity(int size) {
        if (size > capacity) {
            capacity = capacity + (capacity >> 1);
            Object[] temp = new Object[capacity];
            System.arraycopy(elements, 0, temp, 0, this.size);
            elements = temp;
        }
    }
}
