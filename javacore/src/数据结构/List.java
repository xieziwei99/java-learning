package 数据结构;

/**
 * @author 13748
 * 2021-03-23
 */
public interface List<T> {

    void add(T t);

    void add(T t, int index);

    T get(int index);

    int length();

    void delete(int index);

    /**
     * 删除遍历时遇到的第一个
     */
    void delete(T t);

    void deleteAll(T t);
}
