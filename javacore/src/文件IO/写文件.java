package 文件IO;

import java.io.*;

/**
 * @author xieziwei99
 * 2020-05-16
 */
public class 写文件 {

    public static void main(String[] args) throws IOException {
        writeStringToFile("javacore/src/文件IO/hello.txt", "解放军拉萨空间爱多久撒");

        File file = new File("javacore/src/文件IO/azusa.jpg");
        File file1 = new File("javacore/src/文件IO/azusa1.jpg");
//        try (FileInputStream fileInputStream = new FileInputStream(file);
//             FileOutputStream fileOutputStream = new FileOutputStream(file1)) {
//            writeOutputStreamFromInputStream(fileInputStream, fileOutputStream);
//        }

        /*
        缓冲流，是处理流
        作为节点流的外包装使用
        可以加快文件读写速度，原因是内部提供了一个缓冲区
         */
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(file1);
             BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            writeOutputStreamFromInputStream(bis, bos);
        }
    }

    /**
     * 写入文件，若文件存在，则覆盖文件
     * 若 fileName 的路径中包含不存在的文件夹，则先创建文件夹，再写入文件
     * @param fileName 可以只包含文件名，也可以包含路径
     */
    public static void writeStringToFile(String fileName, String msg) throws IOException {
        File file = new File(fileName);
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            boolean mkdirs = parentFile.mkdirs();
            if (mkdirs) {
                System.out.println("创建目录：" + parentFile);
            } else {
                System.out.println("目录 " + parentFile + " 已存在");
            }
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(msg);
            System.out.println("写入文件成功");
        }
    }

    public static void writeOutputStreamFromInputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }
        System.out.println("写入成功");
    }
}
