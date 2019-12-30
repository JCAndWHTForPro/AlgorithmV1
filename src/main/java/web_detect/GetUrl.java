package web_detect;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GetUrl
 * @Author: jicheng
 * @CreateDate: 2019/12/16 9:03 PM
 */
public class GetUrl {

    public static void main(String[] args) throws Exception {
        URL u = new URL("http://www.baidu.com");
        URLConnection urlConnection = u.openConnection();
//        urlConnection.getURL().get
        
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        // 这里就是获取header的方式
        for(Map.Entry<String, List<String>> entry : headerFields.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue().toString());
        }
      //   获取的输入流其实也是内容
        InputStream inputStream = urlConnection.getInputStream();
        int c;
        while((c = inputStream.read()) != -1){
            System.out.print((char) c);
        }
        inputStream.close();
    }
}
