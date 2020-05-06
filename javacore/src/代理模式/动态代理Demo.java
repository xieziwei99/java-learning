package 代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author xieziwei99
 * 2020-05-06
 */
public class 动态代理Demo {

    public static void main(String[] args) {
        Network proxy = (Network) ProxyFactory.getProxy(new Server());
        // 当调用代理对象的方法时，就会去调用 InvocationHandler 接口中的 invoke 实现方法
        proxy.browse();

        /*
        browse开始调用 参数：null
        连接网络
        browse结束调用 返回值：null
         */
    }
}

class ProxyFactory {
    public static Object getProxy(Object o) {
        InvocationHandler handler = (proxy, method, args) -> {
            // proxy：代理对象 一般不使用 proxy 参数
            // System.out.println(proxy.toString());    会导致 StackOverflowError
            // 因为 toString 也是代理对象的方法，当调用代理对象的方法时，就会去调用此 invoke 方法

            String methodName = method.getName();
            System.out.println(String.format("%s开始调用 参数：%s", methodName, Arrays.toString(args)));
            Object ret = method.invoke(o, args);
            System.out.println(String.format("%s结束调用 返回值：%s", methodName, ret));
            return ret;
        };

        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), handler);
    }
}