package niostudy;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件操作方式实验室
 *
 * @ClassName: MappedBufferTry
 * @Author: jicheng
 * @CreateDate: 2020/2/1 12:30 AM
 */
public class MappedBufferTry {

    private static final String FILE_TEST_11 = "/Users/jicheng/Project/temp_file/mapped_buffer_test_1";

    private static final String FILE_TEST_11_MODE = "rw";

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_TEST_11, FILE_TEST_11_MODE);
        FileChannel fileChannel = randomAccessFile.getChannel();
        long size = fileChannel.size();
        byte[] bytes = "jicheng纪成".getBytes();

        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, size, bytes.length);
        // System.out.println(System.getProperty("file.encoding")); 默认使用JVM的file.encoding编码方式，
        // file.encoding不设定使用本机的，本机是UTF-8
//        System.out.println("纪成".getBytes().length);
        map.put(bytes);
        Thread.sleep(10000L);
    }
}
