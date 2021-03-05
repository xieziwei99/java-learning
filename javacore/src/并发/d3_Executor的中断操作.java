package 并发;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，
 * 但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
 *
 * @author xzw
 * 2021-03-05
 */
public class d3_Executor的中断操作 {

    public static void main(String[] args) {
        Runnable[] runnableArray = new Runnable[5];
        for (int i = 0; i < runnableArray.length; i++) {
            int finalI = i;
            runnableArray[i] = () -> {
                try {
                    Thread.sleep(10000);
                    System.out.println("睡眠之后的print" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (Runnable runnable : runnableArray) {
            executorService.execute(runnable);
        }

        // 如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，
        // 它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。
        // fixme: 这段代码为什么没有起作用, runnableArray[2]不是应该停止吗, 还是说我没有理解对
        Future<?> future = executorService.submit(runnableArray[2]);
        future.cancel(true);

        executorService.shutdown();
//        executorService.shutdownNow();
        System.out.println("print in main");
    }
}
