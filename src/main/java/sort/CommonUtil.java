package sort;

import java.util.Random;

/**
 * @ClassName: CommonUtil
 * @Author: jicheng
 * @CreateDate: 2019/11/24 12:46 AM
 */
public class CommonUtil {

    private CommonUtil() {
    }


    public static Integer[] generateIntegerArray(int count, int start, int end) {
        if (count == 0) {
            return new Integer[0];
        }
        Integer[] result = new Integer[count];
        Random random = new Random(37);
        for (int i = 0; i < count; i++) {
            int num = random.nextInt(end - start + 1) + start;
            result[i] = Integer.valueOf(num);
        }

        return result;


    }

    public static <T> void swap(T[] arr, int orginIndex, int newIndex) {
        T temp = arr[orginIndex];
        arr[orginIndex] = arr[newIndex];
        arr[newIndex] = temp;
    }

    public static <T> String arr2String(T[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i].toString()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
