package algorithm;

import java.util.Arrays;
import java.util.Objects;

/**
 * 最小堆
 * [2,3,4,5,6,7,8]
 * [0,1,2,3,4,5,6]
 *
 * @ClassName: MinPriorityQueue
 * @Author: jicheng
 * @CreateDate: 2019/3/31 下午5:43
 */
public class MinPriorityQueue<K extends Comparable<K>> {

    private Object[] elements;

    private int size;

    public MinPriorityQueue() {
        this.elements = new Object[Common.INIT_SIZE];
        this.size = 0;
    }


    public int lchild(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("超出限制");
        }
        return (index * 2) + 1;
    }

    public int rchild(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("超出限制");
        }
        return (index * 2) + 2;
    }


    public int parent(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("超出限制");
        }
        return (index - 1) / 2;
    }

    public void swap(int i, int j) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("超出限制");
        }
        if (j < 0 || j > size) {
            throw new IllegalArgumentException("超出限制");
        }

        K temp = (K) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * 下沉
     *
     * @param index
     */
    public void sink(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("超出限制");
        }
        int p = index;
        while (p < size && lchild(p) < size) {
            K currentEle = (K) elements[p];
            int lchild = lchild(p);
            int rchild = rchild(p);
            K lelement = (K) elements[lchild];
            K relement = (K) elements[rchild];
            int minChildIndex = Objects.isNull(relement) ? lchild :
                    (lelement.compareTo(relement) > 0 ? rchild : lchild);
            if (currentEle.compareTo((K) elements[minChildIndex]) > 0) {
                swap(p, minChildIndex);
                p = minChildIndex;
            } else {
                break;
            }

        }
    }

    /**
     * 上浮
     *
     * @param index
     */
    public void swam(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("超出限制");
        }
        int p = index;
        while (p > 0) {
            K currentEle = (K) elements[p];
            int parentIndex = parent(p);
            if (currentEle.compareTo((K) elements[parentIndex]) < 0) {
                swap(p, parentIndex);
                p = parentIndex;
            } else {
                break;
            }
        }

    }

    /**
     * 添加元素
     *
     * @param element
     */
    public void addElement(K element) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = element;
        swam(size);
        size++;
    }

    /**
     * 删除堆顶元素
     *
     * @return
     */
    public K delElemet() {
        K result = (K) elements[0];
        if (size > 1) {
            swap(0, size - 1);
            elements[--size] = null;
            sink(0);
        }
        return result;
    }

    /**
     * 扩容
     */
    public void resize() {
        int length = elements.length;
        Object[] tempArr = new Object[length + Common.GROW_SIZE];
        System.arraycopy(elements, 0, tempArr, 0, length);
        this.elements = tempArr;
    }


    @Override
    public String toString() {
        return "MinPriorityQueue{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }
}
