package webdetect;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName: BaseWeb
 * @Author: jicheng
 * @CreateDate: 2019/12/15 5:23 PM
 */
public class BaseWeb {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();

        SocketAddress socketAddress = new InetSocketAddress(8080);

        serverSocket.bind(socketAddress);


        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        OutputStream outputStream = accept.getOutputStream();

        StringBuffer sb = new StringBuffer();
        byte[] buffer = new byte[1024];
        int available = inputStream.available();
        System.out.println("可读字节是：" + available);
        int size = 0;
        while ((size = inputStream.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, size));
            available = inputStream.available();
            System.out.println("可读字节是：" + available);

            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"\r\n";
            outputStream.write(date.getBytes());
            if(available == 0) break;
        }
        System.out.println(sb.toString());

        serverSocket.close();
    }
}
