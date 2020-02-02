package niostudy;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @ClassName: ByteTransferTry
 * @Author: jicheng
 * @CreateDate: 2020/2/2 5:04 PM
 */
public class ByteTransferTry {

    public static void main(String[] args) {
        // 普通字符buffer
        CharBuffer charBuffer = CharBuffer.allocate(6);

        // 放数据
        charBuffer.put('中');
        charBuffer.put('文');
        charBuffer.put('知');

        System.out.println("------put after---------");
        System.out.println("limit:" + charBuffer.limit());
        System.out.println("capacity:" + charBuffer.capacity());
        System.out.println("position:" + charBuffer.position());

//        System.out.println(charBuffer.flip().toString());

        System.out.println("------byte transfer after---------");
        // 注意使用的编码方式
        Charset cs = Charset.forName("UTF-8");
        // 字符缓冲到字节缓冲的转换
        charBuffer.flip();
        // 内部根据字符缓冲的大小创建了字节缓冲，并且flip了，编码
        ByteBuffer encodeCharBuffer = cs.encode(charBuffer);
        System.out.println(toHex(encodeCharBuffer).toUpperCase());

        System.out.println("------char transfer after---------");
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        // 放入UTF-8编码的底层数据
        byteBuffer.put((byte)0xE7).put((byte)0x9F).put((byte)0xA5).flip();
        // 解码成具体的字符缓冲
        CharBuffer decodeCharBuffer = cs.decode(byteBuffer);
        System.out.println(decodeCharBuffer.toString());
        System.out.println("limit:" + decodeCharBuffer.limit());
        System.out.println("capacity:" + decodeCharBuffer.capacity());
        System.out.println("position:" + decodeCharBuffer.position());


    }


    private static String toHex(ByteBuffer byteBuffer){
        StringBuffer sb = new StringBuffer();
        while (byteBuffer.hasRemaining()){
            byte b = byteBuffer.get();
            sb.append(String.format("%02x",b & 0xff));
        }
        byteBuffer.rewind();
        return sb.toString();

    }
}
