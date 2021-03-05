package 并发;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。
 * 这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
 *
 * 主要有三种 Executor：
 * CachedThreadPool：一个任务创建一个线程；
 * FixedThreadPool：所有任务只能使用固定大小的线程；
 * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
 *
 * 参考：https://www.cyc2018.xyz/Java/Java%20%E5%B9%B6%E5%8F%91.html#executor
 *
 * @author xzw
 * 2021-03-05
 */
public class d1_Executor的概念和使用 {

    public static void main(String[] args) {
        // Executors 是一个工具类
        // ExecutorService 是一个接口 继承自 Executor 接口
        // CachedThreadPool：一个任务创建一个线程；
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable 执行了一个任务");
    }
}
