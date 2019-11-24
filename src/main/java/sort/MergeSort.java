package sort;

/**
 * @ClassName: MergeSort
 * @Author: jicheng
 * @CreateDate: 2019/11/24 12:38 PM
 */
public class MergeSort<V extends Comparable<V>> {

    public void sort(V[] array) {
        if (array == null || array.length < 1) {
            return;

        }

        sort(array, 0, array.length - 1);
    }

    private void sort(V[] array, int first, int end) {
        if (first >= end) {
            //TODO jicheng 优化建议：b、元素比较少时且近乎有序的数组，直接使用插入排序
            return;
        }

        int mid = (end - first) / 2 + first;
        sort(array, first, mid);
        sort(array, mid + 1, end);
        if (array[mid].compareTo(array[mid + 1]) <= 0) {
            //TODO jicheng 优化建议：a、注意mid和mid+1两个元素有序时，不要merge了
            return;
        }
        merge(array, first, mid, mid + 1, end);

    }

    /**
     * @param array 原始数组
     * @param lf    左边的起始index
     * @param le    左边的结束index
     * @param rf    右边的起始index
     * @param re    右边的结束index
     */
    private void merge(V[] array, int lf, int le, int rf, int re) {

        Object[] tempArray = new Object[re - lf + 1];
        int tempIndex = 0;
        int i = lf;
        int j = rf;

        while (i <= le && j <= re) {
            if (array[i].compareTo(array[j]) < 0) {
                tempArray[tempIndex] = array[i++];
            } else {
                tempArray[tempIndex] = array[j++];
            }
            tempIndex++;
        }
        if (i <= le) {
            for (; i <= le; i++) {
                tempArray[tempIndex++] = array[i];
            }
        } else if (j <= re) {
            for (; j <= re; j++) {
                tempArray[tempIndex++] = array[j];
            }
        }
        System.arraycopy(tempArray, 0, array, lf, tempArray.length);
    }

    public static void main(String[] args) {
        Integer[] arr = {12, 34, 23, 1, 2, 3, 34, 34, 3333};
        MergeSort<Integer> mergeSort = new MergeSort<>();
        mergeSort.sort(arr);
        System.out.println(CommonUtil.arr2String(arr));
    }
}
