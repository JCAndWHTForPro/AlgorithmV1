package algorithm;

import java.util.Arrays;
import java.util.Objects;

/**
 * 索引最小堆
 * [2,3,4,5,6,7,8]
 * [0,1,2,3,4,5,6]
 *
 * @ClassName: MinPriorityQueue
 * @Author: jicheng
 * @CreateDate: 2019/3/31 下午5:43
 */
public class IndexMinPriorityQueue<K extends Comparable<K>> {

    private int[] indexes;

    private Object[] elements;

    private int size;

    public IndexMinPriorityQueue() {
        this.indexes = new int[Common.INIT_SIZE];
        this.elements = new Object[Common.INIT_SIZE];
        this.size = 0;
        for (int i = 0; i < Common.INIT_SIZE; i++) {
            this.indexes[i] = -1;
        }
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
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("超出限制");
        }
        if (j < 0 || j >= size) {
            throw new IllegalArgumentException("超出限制");
        }

        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;
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
            K currentEle = (K) elements[indexes[p]];
            int lchild = lchild(p);
            int rchild = rchild(p);
            K lelement = (K) elements[indexes[lchild]];
            K relement = (K) elements[indexes[rchild]];
            int minChildIndex = Objects.isNull(relement) ? lchild :
                    (lelement.compareTo(relement) > 0 ? rchild : lchild);
            if (currentEle.compareTo((K) elements[indexes[minChildIndex]]) > 0) {
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
            K currentEle = (K) elements[indexes[p]];
            int parentIndex = parent(p);
            K pelement = (K) elements[indexes[parentIndex]];
            if (Objects.nonNull(pelement) && currentEle.compareTo(pelement) < 0) {
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
        indexes[size] = size;
        elements[indexes[size]] = element;
        swam(size++);
    }

    /**
     * 删除堆顶元素
     *
     * @return
     */
    public K delElemet() {
        K result = (K) elements[indexes[0]];
        if (size > 1) {
            swap(0, size - 1);
            elements[indexes[--size]] = null;
            indexes[size] = -1;
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
        int[] tempIndexes = new int[length + Common.GROW_SIZE];
        System.arraycopy(elements, 0, tempArr, 0, length);
        System.arraycopy(indexes, 0, tempIndexes, 0, length);
        for (int i = length; i < tempIndexes.length; i++) {
            tempIndexes[i] = -1;
        }
        this.elements = tempArr;
        this.indexes = tempIndexes;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("索引队列是：").append(Arrays.toString(indexes)).append("\n");
        sb.append("物理元素数组是：[");
        for (int i = 0; i < elements.length; i++) {
            if (i < elements.length - 1) {
                sb.append(elements[i] + " ,");
            } else {
                sb.append(elements[i]).append("]\n");
            }
        }
        sb.append("真实元素数组是：[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                sb.append(elements[indexes[i]] + " ,");
            } else {
                sb.append(elements[indexes[i]]).append("]\n");
            }
        }
        sb.append("元素的数量是：").append(size);

        return sb.toString();
    }
}
