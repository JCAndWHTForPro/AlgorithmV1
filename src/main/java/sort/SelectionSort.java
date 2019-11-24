package sort;

/**
 * 选择排序
 * @ClassName: SelectionSort
 * @Author: jicheng
 * @CreateDate: 2019/11/24 12:45 AM
 */
public class SelectionSort<V extends Comparable<V>> {


    public void sort(V[] array) {
        if(array == null){
            return;
        }
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if(array[j].compareTo(array[i])<0){
                    CommonUtil.swap(array,i,j);
                }
            }
        }
    }


    public static void main(String[] args) {
        Integer[] arr = {12,34,23,1,2,3,34,34,3333};
        SelectionSort<Integer> integerSelectionSort = new SelectionSort<>();
        integerSelectionSort.sort(arr);
        System.out.println(CommonUtil.arr2String(arr));
    }
}
