package 函数式接口;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * @author xzw
 * 2021-01-25
 */
public class Supplier的使用 {

    public static void main(String[] args) throws Exception {
        Supplier<String> stringCallable = () -> "hello world";
        Callable<String> stringCallable2 = () -> "hello world";
        System.out.println(stringCallable.get());
        System.out.println(stringCallable2.call());

        Supplier<Pet> petSupplier = Pet::new;
        Pet pet1 = petSupplier.get();
        System.out.println(pet1);
        Pet pet2 = petSupplier.get();
        pet2.setName("mm");
        pet2.setAge(4);
        System.out.println(pet2);
        System.out.println(pet1);
    }
}

class Pet {
    private String name = "mimi";
    private int age = 3;

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
