package algorithm;

/**
 * Created by jicheng on 2017/4/24.
 * 快排
 */
public class QuickSort {

    private static void exchange(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable para1, Comparable para2) {
        return para1.compareTo(para2) < 0;
    }

    private static void show(Comparable[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                System.out.print(a[i]);
            } else {
                System.out.print("," + a[i]);
            }
        }
        System.out.print("]\n");
    }

    public static void sort(Comparable[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        sort(array, 0, array.length - 1);

    }

    private static void sort(Comparable[] array, int begin, int end) {
        if (begin >= end) {//1、这里判断结束递归的条件，这里可以使用短数组使用插入排序进行优化
            return;
        }
        int j = partition(array, begin, end);
        sort(array, begin, j - 1);
        sort(array, j + 1, end);
    }

    private static int partition(Comparable[] array, int begin, int end) {
        int i = begin, j = end + 1;
        Comparable partitionElem = array[begin];//2、获取切分元素，这里可以优化
        while (true) {
            while (less(array[++i], partitionElem)) {//3、这里判断其实忽略了相等的情况，如果有大量重复元素，这里可以进行优化
                if (i == end) {
                    break;
                }
            }
            while (less(partitionElem, array[--j])) {
                if (j == begin) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(array, i, j);
        }
        exchange(array, begin, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] array = {8,5,7,1,9,11,2,22,39,0,8,-6};
        sort(array);
        show(array);
    }
}
