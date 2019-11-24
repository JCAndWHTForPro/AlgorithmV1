package sort;

/**
 * @ClassName: QuickSort
 * @Author: jicheng
 * @CreateDate: 2019/11/24 1:27 PM
 */
public class QuickSort<V extends Comparable<V>> {


    public void sort(V[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        sort3(array, 0, array.length - 1);
    }

    /**
     * 第一种排序算法，相对来说，没有考虑很多种情况
     *
     * @param array
     * @param startPos
     * @param endPos
     */
    private void sort1(V[] array, int startPos, int endPos) {
        if (startPos >= endPos) {
            return;
        }
        V sentinel = array[startPos];
        int leftEndPos = startPos;
        for (int i = startPos + 1; i <= endPos; i++) {
            if (array[i].compareTo(sentinel) < 0) {
                CommonUtil.swap(array, i, ++leftEndPos);
            }
        }
        CommonUtil.swap(array, startPos, leftEndPos);

        sort1(array, startPos, leftEndPos - 1);
        sort1(array, leftEndPos + 1, startPos);

    }

    /**
     * 两路快排
     *
     * @param array
     * @param startPos
     * @param endPos
     */
    private void sort2(V[] array, int startPos, int endPos) {

        if (startPos >= endPos) {
            return;
        }

        V sentinel = array[startPos];
        int i = startPos + 1;
        int j = endPos;
        while (true) {
            while (i <= j && array[i].compareTo(sentinel) < 0) i++;
            while (j >= i && array[j].compareTo(sentinel) > 0) j--;
            if (j < i) {
                break;
            }

            CommonUtil.swap(array, i++, j--);

        }

        CommonUtil.swap(array, startPos, j);

        sort2(array, startPos, j - 1);
        sort2(array, j + 1, endPos);

    }


    /**
     * 三路快排
     * @param array
     * @param startPos
     * @param endPos
     */
    private void sort3(V[] array, int startPos, int endPos) {
        if (startPos >= endPos) {
            return;
        }

        V sentinel = array[startPos];
        int i = startPos + 1;// 游标
        int leftIndex = startPos;
        int rightIndex = endPos + 1;

        while (i < rightIndex) {
            if (array[i].compareTo(sentinel) < 0) {
                CommonUtil.swap(array, ++leftIndex, i++);
            } else if (array[i].compareTo(sentinel) > 0) {
                CommonUtil.swap(array, i, --rightIndex);
            } else {
                i++;
            }
        }
        // TODO jicheng 此处的rightIndex处在比哨兵值大的区域里面的第一个位置，所以要先减一，用于交换哨兵
        CommonUtil.swap(array, startPos, rightIndex-1);
        // TODO jicheng 此处leftIndex处在小于哨兵的数值中的最后一个位置
        sort3(array, startPos, leftIndex);
        // TODO jicheng 此处rightIndex处在大于哨兵的数值中的第一个位置
        sort3(array, rightIndex, endPos);
    }

    public static void main(String[] args) {
        Integer[] arr = CommonUtil.generateIntegerArray(10, 0, 200);
        QuickSort<Integer> mergeSort = new QuickSort<>();
        mergeSort.sort(arr);
        System.out.println(CommonUtil.arr2String(arr));
    }
}
