package 并发;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * await() signal() signalAll() 的简单使用
 * 相比于 wait() 这种等待方式，await() 可以指定等待的条件，因此更加灵活。
 * 那么如何指定等待条件呢 还不清楚
 *
 * 参考 https://www.cyc2018.xyz/Java/Java%20%E5%B9%B6%E5%8F%91.html#await-signal-signalall
 *
 * @author xzw
 * 2021-03-06
 */
public class d7_await和signal的使用 {

    public static void main(String[] args) {
        BankAccount1 account = new BankAccount1();
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

class BankAccount1 {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int money;

    public BankAccount1() {
        this(0);
    }

    public BankAccount1(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "money=" + money +
                '}';
    }

    public void deposit(int delta) {
        lock.lock();
        try {
            System.out.println("当前 " + this.money);
            this.money += delta;
            System.out.println("存入 " + delta + " 后金额为 " + this.money);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int delta) {
        lock.lock();
        try {
            while (this.money - delta < 0) {
                try {
                    System.out.println("\t\t没钱了，等一等");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\t\t当前 " + this.money);
            this.money -= delta;
            System.out.println("\t\t取出 " + delta + " 后金额为 " + this.money);
        } finally {
            lock.unlock();
        }
    }
}