package 并发;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 可用于异步获取执行结果或取消执行任务的场景。
 * 当一个计算任务需要执行很长时间，那么就可以用 FutureTask 来封装这个任务，
 * 主线程在完成自己的任务之后再去获取结果。
 *
 * @author xzw
 * 2021-03-10
 */
public class d11_FutureTask的使用 {

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            int ret = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100);
                ret += i;
            }
            return ret;
        });
        Thread t = new Thread(futureTask);
        t.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("do some other thing");
        });
        t2.start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
