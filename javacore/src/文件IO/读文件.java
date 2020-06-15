package 文件IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author xieziwei99
 * 2020-05-16
 */
public class 读文件 {

    public static void main(String[] args) throws IOException {
        File file = new File("javacore/src/文件IO/hello.txt");
        System.out.println(readFileToString(file));
    }

    public static String readFileToString(File file) throws IOException {
        StringBuilder ret = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            char[] buf = new char[1024];
            int len;
            while ((len = br.read(buf)) != -1) {
                ret.append(buf, 0, len);
            }
        }
        return ret.toString();
    }
}
