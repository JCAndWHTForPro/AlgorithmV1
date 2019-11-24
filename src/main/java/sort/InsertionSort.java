package sort;

/**
 * @ClassName: InsertionSort
 * @Author: jicheng
 * @CreateDate: 2019/11/24 12:56 AM
 */
public class InsertionSort<V extends Comparable<V>> {


    public void sort(V[] array) {
        if(array == null){
            return;
        }
        int length = array.length;
        for (int i = 1; i < length; i++) {

            int j = i;
            V temp = array[i];
            while (j > 0) {
                if (temp.compareTo(array[j - 1]) < 0) {
                    array[j] = array[j - 1];
                    j--;
                } else {
                    break;
                }
            }
            array[j] = temp;

        }
    }

    public static void main(String[] args) {
        Integer[] arr = {12, 34, 23, 1, 2, 3, 34, 34, 3333};
        InsertionSort<Integer> integerSelectionSort = new InsertionSort<>();
        integerSelectionSort.sort(arr);
        System.out.println(CommonUtil.arr2String(arr));
    }
}
