package sort;

/**
 * @ClassName: CommonUtil
 * @Author: jicheng
 * @CreateDate: 2019/11/24 12:46 AM
 */
public class CommonUtil {

    private CommonUtil() {
    }


    public static <T> void swap(T[] arr, int orginIndex, int newIndex) {
        T temp = arr[orginIndex];
        arr[orginIndex] = arr[newIndex];
        arr[newIndex] = temp;
    }

    public static <T> String arr2String(T[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i].toString()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
