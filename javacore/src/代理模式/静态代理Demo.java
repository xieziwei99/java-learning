package 代理模式;

/**
 * @author xieziwei99
 * 2020-05-06
 */
public class 静态代理Demo {
    public static void main(String[] args) {
        Network network = new ServerProxy(new Server());
        network.browse();
        /*
        运行结果：
            联网前的检查
            连接网络
            联网后的处理
         */
    }
}

/**
 * 接口
 * 让代理类和被代理类都实现同一个接口 好处是被代理类对代理类是透明的
 */
interface Network {
    void browse();
}

/**
 * 被代理类
 */
class Server implements Network {

    @Override
    public void browse() {
        System.out.println("连接网络");
    }
}

/**
 * 代理类
 */
class ServerProxy implements Network {

    private final Network network;

    public ServerProxy(Network network) {
        this.network = network;
    }

    private void checkBefore() {
        System.out.println("联网前的检查");
    }

    private void checkAfter() {
        System.out.println("联网后的处理");
    }

    @Override
    public void browse() {
        this.checkBefore();
        network.browse();
        this.checkAfter();
    }
}