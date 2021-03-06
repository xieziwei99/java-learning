package 并发;

import java.util.Random;

/**
 * wait() notify() notifyAll() 都属于 Object 的一部分，而不属于 Thread。
 * <p>
 * 只能在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
 * <p>
 * 使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的
 * 同步方法或者同步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。
 *
 * 参考：https://www.cyc2018.xyz/Java/Java%20%E5%B9%B6%E5%8F%91.html#wait-notify-notifyall
 *
 * @author xzw
 * 2021-03-06
 */
public class d6_wait和notify的使用 {

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Thread t1 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                account.deposit(random.nextInt(10));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                account.withdraw(random.nextInt(10) + 10);
            }
        });
        t1.start();
        t2.start();
    }
}

class BankAccount {
    private int money;

    public BankAccount() {
        this(0);
    }

    public BankAccount(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "money=" + money +
                '}';
    }

    public void deposit(int delta) {
        synchronized (this) {
            System.out.println("当前 " + this.money);
            this.money += delta;
            System.out.println("存入 " + delta + " 后金额为 " + this.money);
            this.notifyAll();
        }
    }

    public void withdraw(int delta) {
        synchronized (this) {
            while (this.money - delta < 0) {
                try {
                    System.out.println("\t\t没钱了，等一等");
                    this.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\t\t当前 " + this.money);
            this.money -= delta;
            System.out.println("\t\t取出 " + delta + " 后金额为 " + this.money);
        }
    }
}