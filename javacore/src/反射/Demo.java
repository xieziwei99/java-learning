package 反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xieziwei99
 * 2020-07-10
 */
public class Demo {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        /*
        getConstructor:
            1. 只得到 public
         */
        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        Person san = constructor.newInstance("san", 18);
        System.out.println(san);

        Field age = personClass.getDeclaredField("age");
        age.set(san, 20);
        System.out.println(san);

        Method sayHello = personClass.getDeclaredMethod("sayHello");
        sayHello.invoke(san);
        System.out.println("========================");

        // 使用反射调用私有的构造器、成员变量、方法
        Constructor<Person> constructor1 = personClass.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        Person si = constructor1.newInstance("si");
        System.out.println(si);

        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(si, "LiSi");
        System.out.println(si);

        Method say = personClass.getDeclaredMethod("say", String.class);
        say.setAccessible(true);
        String hello = (String) say.invoke(si, "hello");
        System.out.println(hello);
    }
}

@SuppressWarnings("unused")
class Person {
    public int age;
    private String name;

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void sayHello() {
        System.out.println("Hello, my name is " + name);
    }

    private String say(String text) {
        System.out.println(name + " says: " + text);
        return text;
    }
}