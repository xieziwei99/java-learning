## 动态代理

通用类

```java
class ProxyFactory {
    public static Object getProxy(Object o) {
        InvocationHandler handler = (proxy, method, args) -> {
            String methodName = method.getName();
            System.out.println(String.format("%s开始调用 参数：%s", methodName, Arrays.toString(args)));
            Object ret = method.invoke(o, args);
            System.out.println(String.format("%s结束调用 返回值：%s", methodName, ret));
            return ret;
        };

        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), handler);
    }
}
```

proxy：代理对象 一般不使用 proxy 参数
System.out.println(proxy.toString());    会导致 StackOverflowError
因为 toString 也是代理对象的方法，当调用代理对象的方法时，就会去调用此 invoke 方法