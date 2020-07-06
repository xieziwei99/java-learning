package 文件IO;

import java.io.*;

/**
 * @author xieziwei99
 * 2020-07-06
 */
public class 数据流使用 {

    public static void main(String[] args) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("javacore/src/文件IO/数据流使用.txt"))) {
            dos.writeUTF("hello world");
            dos.writeInt(688);
            dos.writeBoolean(true);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取顺序与写入顺序必须一致
        try (DataInputStream dis = new DataInputStream(new FileInputStream("javacore/src/文件IO/数据流使用.txt"))) {
            System.out.println(dis.readUTF());
            System.out.println(dis.readInt());
            System.out.println(dis.readBoolean());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
