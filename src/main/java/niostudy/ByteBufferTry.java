package niostudy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Buffer类的实操
 * @ClassName: BufferTry
 * @Author: jicheng
 * @CreateDate: 2020/1/26 3:23 PM
 */
public class ByteBufferTry {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(34);
        // limit capacity position remark
        System.out.println("-------init----------");
        System.out.println("limit:" + byteBuffer.limit());// 34
        System.out.println("capacity:" + byteBuffer.capacity());// 34
        System.out.println("position:" + byteBuffer.position());// 0
//        System.out.println("limit:"+byteBuffer.mark());


        byteBuffer.put((byte) 'H')
                .put((byte) 'e')
                .put((byte) 'l')
                .put((byte) 'l')
                .put((byte) 'o');

        System.out.println("-------put data after----------");
        System.out.println("limit:" + byteBuffer.limit());// 34
        System.out.println("capacity:" + byteBuffer.capacity());// 34
        System.out.println("position:" + byteBuffer.position());// 5

        ByteOrder order = byteBuffer.order();
        System.out.println(order);
        System.out.println(ByteOrder.nativeOrder());

        CharBuffer charBuffer = CharBuffer.allocate(23);
        System.out.println(charBuffer.order());



    }
}
