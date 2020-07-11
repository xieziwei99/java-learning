package 反射;

/**
 * @author xieziwei99
 * 2020-07-11
 */
public class 获取Class的实例的方式 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 1.
        Class<?> personClass = Class.forName("反射.Person");
        System.out.println(personClass);

        // 2.
        Class<Person> personClass1 = Person.class;
        // 3.
        Person person = new Person("Jack", 18);
        Class<? extends Person> personClass2 = person.getClass();
        // 4.
        ClassLoader classLoader = 获取Class的实例的方式.class.getClassLoader();
        Class<?> personClass3 = classLoader.loadClass("反射.Person");

        System.out.println(personClass == personClass1);
        System.out.println(personClass == personClass2);
        System.out.println(personClass == personClass3);
    }
}
