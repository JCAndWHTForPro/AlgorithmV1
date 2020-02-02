package niostudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @ClassName: TraditionIoTry
 * @Author: jicheng
 * @CreateDate: 2020/2/1 4:09 PM
 */
public class TraditionIOTry {

    private static final String FILE_TEST_11 = "/Users/jicheng/Project/temp_file/mapped_buffer_test_1";

    public static void main(String[] args) throws Exception {
        File file = new File(FILE_TEST_11);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))
        ) {
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

    }
}
