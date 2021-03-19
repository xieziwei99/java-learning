package 数据结构;

import java.util.NoSuchElementException;

/**
 * @author xzw
 * 2021-03-18
 */
public class HashMap {
    private final int capacity = 16;
    private final Node[] elements = new Node[capacity];

    @SuppressWarnings("unused")
    private static int hash(String key) {
        switch (key) {
            case "hello": return 1;
            case "world":
            case "hash":
            case "code":
                return 2;
            case "java":
            case "python":
                return 3;
            default:
                return 0;
        }
    }

    public void put(String key, int value) {
        // hashCode 可能返回负值
        int hash = Math.abs(key.hashCode() % capacity);
//        int hash = hash(key);       // 测试用
        Node newNode = new Node(key, value);
        if (elements[hash] == null) {
            elements[hash] = newNode;
        } else {
            Node p = elements[hash];
            while (p != null) {
                if (p.key.equals(key)) {
                    p.value = value;
                    return;     // 找到了 key 值相同的 Node
                }
                p = p.next;
            }
            // 没有在该链表上找到 key 值相同的 Node，意味着是一个新的 key 值
            p = elements[hash].next;
            elements[hash].next = newNode;
            newNode.next = p;
        }
    }

    public int get(String key) {
        // hashCode 可能返回负值
        int hash = Math.abs(key.hashCode() % capacity);
//        int hash = hash(key);       // 测试用
        if (elements[hash] != null) {
            Node p = elements[hash];
            while (p != null) {
                if (p.key.equals(key)) {
                    return p.value;     // 找到了 key 值相同的 Node
                }
                p = p.next;
            }
        }
        // 没有在该链表上找到 key 值相同的 Node
        throw new NoSuchElementException("找不到 key 值为 " + key + " 的记录");
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (Node element : elements) {
            ret.append(element).append("\n");
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("hello", 1);
        hashMap.put("world", 1);
        hashMap.put("java", 1);
        hashMap.put("hash", 1);
        hashMap.put("code", 1);
        hashMap.put("python", 1);
        hashMap.put("a", 1);
        hashMap.put("b", 1);
        hashMap.put("c", 1);

        hashMap.put("b", 10);
        hashMap.put("code", 100);
        System.out.println(hashMap);

        System.out.println(hashMap.get("hello"));
        System.out.println(hashMap.get("b"));
        System.out.println(hashMap.get("code"));
        System.out.println(hashMap.get("d"));
    }
}

class Node {
    String key;
    int value;
    Node next;

    public Node(String key, int value) {
        this.key = key;
        this.value = value;
        next = null;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")" + " -> " + next;
    }
}