package sort;

/**
 * @ClassName: BubbleSort
 * @Author: jicheng
 * @CreateDate: 2019/11/24 12:22 PM
 */
public class BubbleSort<V extends Comparable<V>> {


    public void sort(V[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = length - 1; j > i; j--) {
                if (array[j].compareTo(array[j - 1]) < 0) {
                    CommonUtil.swap(array, j, j - 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        Integer array[] = {112, 32, 1, 24, 2342, 3, 12, 0, 234, 3};
        new BubbleSort<Integer>().sort(array);
        System.out.println(CommonUtil.arr2String(array));

    }
}
