package 并发;

/**
 * @author xzw
 * 2021-03-06
 */
public class d5_join的使用 {

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                threadA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b");
        });
        threadA.start();
        threadB.start();
    }
}
