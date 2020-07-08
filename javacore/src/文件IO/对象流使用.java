package 文件IO;

import java.io.*;

/**
 * @author xieziwei99
 * 2020-07-08
 */
public class 对象流使用 {

    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            oos.writeObject(new Person("san", 18, new Pet("hong", 3)));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"))) {
            Person o = (Person) ois.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
/*
序列化
    1. 实现 Serializable 接口的类必须提供 serialVersionUID
        - 没有提供 serialVersionUID 的类也会根据类的成员变量自动生成
        - 所以不提供会存在一个问题：类的成员变量发生修改后，serialVersionUID 就变了，
          之前序列化过的对象无法再被反序列化
    2. 可序列化的类要求其成员变量也都可以序列化
 */
class Person implements Serializable {
    public static final long serialVersionUID = 20171017L;

    public String name;
    public int age;
    public Pet pet;

    public Person(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pet=" + pet +
                '}';
    }
}

class Pet implements Serializable {
    public static final long serialVersionUID = 20171016L;

    public String name;
    public int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
