package 并发;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch
 * 维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，
 * 那些因为调用 await() 方法而在等待的线程就会被唤醒。
 *
 * @author xzw
 * 2021-03-06
 */
public class d8_CountDownLatch的使用 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        // 通过更改线程池的大小可以明显看出，线程是每3个3个的一执行
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println("run... " + finalI);
                countDownLatch.countDown();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("print in main");
        executorService.shutdown();
    }
}
