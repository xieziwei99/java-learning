package 并发;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 和 CountdownLatch 相似，都是通过维护计数器来实现的。
 * 不同的是，线程执行 await() 方法之后计数器会减 1，并进行等待，直到计数器为 0，
 * 所有调用 await() 方法而在等待的线程才能继续执行。（相当于同步屏障）
 *
 * CyclicBarrier 和 CountdownLatch 的一个区别是，CyclicBarrier 的计数器通过调用 reset() 方法
 * 可以循环使用，所以它才叫做循环屏障。（还没有体现）
 *
 * CyclicBarrier 有两个构造函数，其中 parties 指示计数器的初始值，
 * barrierAction 在所有线程都到达屏障的时候会执行一次。
 *
 * 参考：https://www.cyc2018.xyz/Java/Java%20%E5%B9%B6%E5%8F%91.html#cyclicbarrier
 *
 * @author xzw
 * 2021-03-07
 */
public class d9_CyclicBarrier的使用 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("大家都同步了呢"));
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println("before " + finalI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("after " + finalI);
            });
        }
        executorService.shutdown();
    }
}
