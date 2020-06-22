package 设计模式.单例模式;

/**
 * @author xieziwei99
 * 2020-06-22
 */
public class SingletonDemo {

    public static void main(String[] args) {
        main1();
        main2();
    }

    public static void main1() {
        // 测试饿汉式
        Bank bank1 = Bank.getInstance();
        bank1.setTotal(123);
        Bank bank3 = Bank.getInstance();
        System.out.println(bank3.getTotal());   // 123
    }

    public static void main2() {
        // 测试懒汉式
        Sun sun = Sun.getInstance();
        sun.runCircle();

        Sun sun2 = Sun.getInstance();
        System.out.println(sun == sun2);    // true
    }
}

// 饿汉式：线程安全
class Bank {
    private double total;

    private Bank(double total) {
        this.total = total;
    }

    private static final Bank bank = new Bank(0);

    public static Bank getInstance() {
        return bank;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}

// 懒汉式：延迟对象的创建
class Sun {
    private final double radius;

    private Sun(double radius) {
        this.radius = radius;
    }

    private static Sun sun = null;

    public static Sun getInstance() {
        if (sun == null) {
            sun = new Sun(120);
        }
        return sun;
    }

    public void runCircle() {
        System.out.println("running " + radius);
    }
}