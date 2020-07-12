package 反射;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xieziwei99
 * 2020-07-12
 */
public class 通过ClassLoader可以加载配置文件 {

    public static void main(String[] args) throws IOException {
        // 1. 通过 Properties 加载
        Properties properties = new Properties();
        // 路径相对于项目工程目录
        FileInputStream fis = new FileInputStream("javacore/src/反射/jdbc.properties");
        properties.load(fis);
        System.out.println(properties.getProperty("username"));
        System.out.println(properties.getProperty("password"));

        // 2. 通过 ClassLoader 加载
        ClassLoader classLoader = 通过ClassLoader可以加载配置文件.class.getClassLoader();
        // 此路径相对于 src 目录
        InputStream is = classLoader.getResourceAsStream("反射/jdbc.properties");
        properties.load(is);
        System.out.println(properties.getProperty("username"));
        System.out.println(properties.getProperty("password"));
    }
}
