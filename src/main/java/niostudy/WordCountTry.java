package niostudy;

import com.google.common.primitives.Bytes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: WordCountTry
 * @Author: jicheng
 * @CreateDate: 2020/2/2 6:36 PM
 */
public class WordCountTry {

    private static void nioCount(String fileName) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
             FileChannel fileChannel = randomAccessFile.getChannel()) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY
                    , 0, fileChannel.size());

            mappedByteBuffer.limit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void fileChannelIoCount(String fileName) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
             FileChannel fileChannel = randomAccessFile.getChannel()) {
            ByteBuffer bb = ByteBuffer.allocate(8*1024);
            int sum;
            while ((sum = fileChannel.read(bb)) != -1) {
                // do something
                bb.flip();
                Charset charset = Charset.forName("GBK");
                CharBuffer decode = charset.decode(bb);
                bb.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void traditionalIoCount(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, "GBK"))
             ) {
//            char[] buffer = new char[8 * 1024];
//            long sum = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
//            while ((line = br.readLine()) != null) {
                // do count
//                System.out.println(string);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        nioCount("/Users/jicheng/Project/temp_file/Jane Eyre(简·爱).txt");
        System.out.println("nio mili:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        traditionalIoCount("/Users/jicheng/Project/temp_file/Jane Eyre(简·爱).txt");
        System.out.println("traditional mili:" + (System.currentTimeMillis() - begin));


        begin = System.currentTimeMillis();
        fileChannelIoCount("/Users/jicheng/Project/temp_file/Jane Eyre(简·爱).txt");
        System.out.println("filechanell mili:" + (System.currentTimeMillis() - begin));
    }
}
