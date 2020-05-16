package 文件IO;

import java.io.File;
import java.io.IOException;

/**
 * @author xieziwei99
 * 2020-05-16
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        // 允许使用 Unix 的分隔符 `/`
        File file = new File("javacore/src/文件IO/hello.txt");
        boolean newFile = file.createNewFile();
        if (newFile) {
            System.out.println("创建成功");
        } else {
            System.out.println("文件已存在");
        }
    }
}
