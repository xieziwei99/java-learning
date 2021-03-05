package 并发;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xzw
 * 2021-03-05
 */
public class d4_synchronize的使用 {

    public static void main(String[] args) {
//        Account account = new Account();
//        AccountWithSynchronize account = new AccountWithSynchronize();
        AccountWithReentrantLock account = new AccountWithReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(() -> {
            for (int i = 0; i < 100000; i++) {
                account.add();
            }
        });
        executorService.execute(() -> {
            for (int i = 0; i < 100000; i++) {
                account.sub();
            }
        });
        executorService.shutdown();
    }
}

class Account {
    private int money;

    public Account() {
        this(0);
    }

    public Account(int money) {
        this.money = money;
    }

    public void add() {
        money++;
        System.out.println("add " + money);
    }

    public void sub() {
        money--;
        System.out.println("sub " + money);
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                '}';
    }
}

class AccountWithSynchronize {
    private int money;

    public AccountWithSynchronize() {
        this(0);
    }

    public AccountWithSynchronize(int money) {
        this.money = money;
    }

    public void add() {
        synchronized (this) {
            money++;
            System.out.println("add " + money);
        }
    }

    public void sub() {
        synchronized (this) {
            money--;
            System.out.println("sub " + money);
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                '}';
    }
}

class AccountWithReentrantLock {
    private int money;
    private final Lock lock = new ReentrantLock();

    public AccountWithReentrantLock() {
        this(0);
    }

    public AccountWithReentrantLock(int money) {
        this.money = money;
    }

    public void add() {
        lock.lock();
        try {
            money++;
            System.out.println("add " + money);
        } finally {
            lock.unlock();
        }
    }

    public void sub() {
        lock.lock();
        try {
            money--;
            System.out.println("sub " + money);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                '}';
    }
}