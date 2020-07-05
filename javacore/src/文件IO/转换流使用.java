package 文件IO;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author xieziwei99
 * 2020-07-05
 */
public class 转换流使用 {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("javacore/src/文件IO/hello.txt");
             FileOutputStream fos = new FileOutputStream("javacore/src/文件IO/hello_gbk.txt");
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk")
        ) {
            char[] buf = new char[1024];
            int len;
            while (-1 != (len = isr.read(buf))) {
                osw.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
