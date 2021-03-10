package 并发;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BlockingQueue
 * 提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；
 * 如果队列为满 put() 将阻塞，直到队列有空闲位置。
 * @author xzw
 * 2021-03-10
 */
public class d12_BlockingQueue的使用 {
    private static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    private static class Producer implements Runnable {

        @Override
        public void run() {
            try {
                queue.put("product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("produce..");
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            String take = "";
            try {
                take = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I consume a " + take);
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            executorService.execute(new Producer());
        }
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Consumer());
        }
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Producer());
        }
        executorService.shutdown();
    }
}
