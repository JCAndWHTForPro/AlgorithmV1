package niostudy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 测试文件拷贝的效率
 * @ClassName: FileCopy
 * @Author: jicheng
 * @CreateDate: 2020/1/31 4:00 PM
 */
public class FileCopy {

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        // 传统的拷贝
        Path path1 = Paths.get("/Users/jicheng/Project/temp_file/copy_file_1");
        Path path2 = Paths.get("/Users/jicheng/Project/temp_file/copy_file_2");
        try {
            Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统的拷贝时间：" + (end - begin));

        begin = System.currentTimeMillis();
        try {
            RandomAccessFile randomAccessFile1 = new RandomAccessFile("/Users/jicheng/Project/temp_file/copy_file_11", "rw");
            FileChannel fromChannel = randomAccessFile1.getChannel();
            RandomAccessFile randomAccessFile2 = new RandomAccessFile("/Users/jicheng/Project/temp_file/copy_file_22", "rw");
            FileChannel toChannel = randomAccessFile2.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("新的拷贝时间：" + (end - begin));

    }
}
