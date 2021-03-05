package 并发;

/**
 * InterruptedException
 * 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，
 * 那么就会抛出 InterruptedException，从而提前结束该线程。
 * 但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
 *
 * 参考: https://www.cyc2018.xyz/Java/Java%20%E5%B9%B6%E5%8F%91.html#interruptedexception
 *
 * @author xzw
 * 2021-03-05
 */
public class d2_中断异常的概念 {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable1());
//        Thread thread = new Thread(new MyRunnable2());
        thread.start();
        thread.interrupt();
        System.out.println("main 的打印");

        /* 程序输出
        main 的打印
        java.lang.InterruptedException: sleep interrupted
	        at java.lang.Thread.sleep(Native Method)
	        at 并发.MyRunnable1.run(中断异常的概念.java:26)
         */
    }
}

class MyRunnable1 implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("MyRunnable1---睡眠之后的print");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException
 * 的操作，那么调用线程的 interrupt() 方法就无法使线程提前结束。
 *
 * 但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。
 * 因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
 */
class MyRunnable2 implements Runnable {

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("线程进入了无限循环");
        }
        System.out.println("线程脱离了无限循环");
    }
}
