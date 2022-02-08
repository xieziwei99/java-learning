package 并发;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xzw
 * 2022-02-08
 */
public class d13_DelayQueued的使用 {
    public static void main(String[] args) {
        MySystem system = new MySystem();
        Buyer buyer1 = new Buyer("z3");
        Buyer buyer2 = new Buyer("l4");
        AtomicReference<Order> order1 = new AtomicReference<>();
        AtomicReference<Order> order2 = new AtomicReference<>();
        AtomicReference<Order> order3 = new AtomicReference<>();
        new Thread(() -> {
            try {
                order1.set(system.createOrder(buyer1, 1));
                Thread.sleep(1000);
                order2.set(system.createOrder(buyer2, 2));
                Thread.sleep(2000);
                order3.set(system.createOrder(buyer1, 3));
                Thread.sleep(2000);
                buyer1.payAnOrder(order1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println(order1.get());
                System.out.println(order2.get());
                System.out.println(order3.get());
                Order poll = system.DELAY_QUEUE.poll();
                if (poll != null && !poll.isAlreadyPay()) {
                    system.cancelOrder(poll);
                }
                try {
                    //noinspection BusyWait
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class Order implements Delayed {

    private static final long TTL = TimeUnit.SECONDS.toMillis(10);

    private int id;
    private long createTime;
    private boolean alreadyPay;
    private boolean canceled;

    public Order(int id) {
        this.id = id;
        this.createTime = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public boolean isAlreadyPay() {
        return alreadyPay;
    }

    public void pay() {
        this.alreadyPay = true;
    }

    public void cancel() {
        this.canceled = true;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(getCreateTime() + TTL - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long diff;
        if (o instanceof Order) {
            diff = this.getCreateTime() - ((Order) o).getCreateTime();
        } else {
            diff = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        }
        return diff < 0 ? -1 : (diff == 0 ? 0 : 1);
    }

    @Override
    public String toString() {
        if (alreadyPay) {
            return "订单已支付。ID: " + getId();
        } else {
            if (canceled) {
                return "订单已取消。ID: " + getId();
            } else {
                return String.format("订单未支付。ID: %s, 即将超时: %s秒", getId(), getDelay(TimeUnit.SECONDS));
            }
        }
    }
}


class Buyer {
    private String name;

    public Buyer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void payAnOrder(Order order) {
        String format = String.format("%s支付了订单。ID: %s", getName(), order.getId());
        System.out.println(format);
        order.pay();
    }
}

class MySystem {

    public DelayQueue<Order> DELAY_QUEUE = new DelayQueue<>();

    public Order createOrder(Buyer buyer, int orderId) {
        String format = String.format("%s创建了订单。ID: %s", buyer.getName(), orderId);
        System.out.println(format);
        Order order = new Order(orderId);
        DELAY_QUEUE.put(order);
        return order;
    }

    public void cancelOrder(Order order) {
        String format = String.format("订单15min未支付，系统已自动取消。ID: %s", order.getId());
        System.out.println(format);
        order.cancel();
    }
}