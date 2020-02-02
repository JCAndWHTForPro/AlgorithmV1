package niostudy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Buffer类的实操
 * @ClassName: BufferTry
 * @Author: jicheng
 * @CreateDate: 2020/1/26 3:23 PM
 */
public class ByteBufferTry {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
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

        System.out.println("-------flip data after----------");
        byteBuffer.flip();
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("position:" + byteBuffer.position());


        System.out.println("-------clear data after----------");
        byteBuffer.clear();
        System.out.println("limit:" + byteBuffer.limit());
        System.out.println("capacity:" + byteBuffer.capacity());
        System.out.println("position:" + byteBuffer.position());

        System.out.println(byteBuffer.order());

        /*ByteOrder order = byteBuffer.order();
        System.out.println(order);

        CharBuffer charBuffer = CharBuffer.allocate(23);
        System.out.println(charBuffer.order());*/



    }
}
