package algorithm;

import java.util.Arrays;
import java.util.Objects;

/**
 * 索引最小堆
 * [0,  1,  2,  3,  4,  5]
 * [2,  4,  1,  0,  3,  5]
 * [55 ,23 ,4 ,43 ,14 ,41]
 * [3,  2,  0,  4,  1,  5]
 *
 * @ClassName: MinPriorityQueue
 * @Author: jicheng
 * @CreateDate: 2019/3/31 下午5:43
 */
public class IndexMinPriorityQueue<K extends Comparable<K>> {

    private int[] indexes;

    private Object[] elements;

    private int[] recs;

    private int size;

    public IndexMinPriorityQueue() {
        this.indexes = new int[Common.INIT_SIZE];
        this.elements = new Object[Common.INIT_SIZE];
        this.recs = new int[Common.INIT_SIZE];
        this.size = 0;
        for (int i = 0; i < Common.INIT_SIZE; i++) {
            this.indexes[i] = -1;
            this.recs[i] = -1;
        }
    }

    public IndexMinPriorityQueue(int count) {
        this.indexes = new int[count];
        this.elements = new Object[count];
        this.recs = new int[count];
        this.size = 0;
        for (int i = 0; i < count; i++) {
            this.indexes[i] = -1;
            this.recs[i] = -1;
        }
    }

    public void insert(int index, K elem) {
        if (index < 0 || index >= elements.length) {
            throw new IllegalArgumentException("超出限制");
        }
//        i += 1;
        elements[index] = elem;
        indexes[size] = index;
        recs[index] = size;
        size++;

        swam(size - 1);
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

        recs[indexes[i]] = i;
        recs[indexes[j]] = j;
    }


    /**
     * 下沉
     *
     * @param index
     */
    public void sink(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("超出限制");
        }
        int p = index;
        while (p < size && lchild(p) < size) {
            K currentEle = (K) elements[indexes[p]];
            int lchild = lchild(p);
            int rchild = rchild(p);
            K lelement = (K) elements[indexes[lchild]];
            int minChildIndex = indexes[rchild] == -1 ? lchild :
                    (lelement.compareTo((K) elements[indexes[rchild]]) > 0 ? rchild : lchild);
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
        if (index < 0 || index >= size) {
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
        recs[size] = size;
        elements[indexes[size]] = element;
        swam(size++);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contain(K element) {
        for (int i = 0; indexes[i] != -1; i++) {
            Object element1 = this.elements[indexes[i]];
            if (element1.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean contain(int index) {
        return this.recs[index] != -1;
    }

    /**
     * 删除堆顶元素
     *
     * @return
     */
    public K delElemet() {
        K result = (K) elements[indexes[0]];
        delEl();
        return result;
    }

    public int delElemetIndex() {
        int result = indexes[0];
        delEl();
        return result;
    }

    private void delEl() {
        if (size > 1) {
            swap(0, size - 1);
        }
        elements[indexes[--size]] = null;
        recs[indexes[size]] = -1;
        indexes[size] = -1;
        sink(0);
    }

    /**
     * 扩容
     */
    public void resize() {
        int length = elements.length;
        Object[] tempArr = new Object[length + Common.GROW_SIZE];
        int[] tempIndexes = new int[length + Common.GROW_SIZE];
        int[] tempRecs = new int[length + Common.GROW_SIZE];
        System.arraycopy(elements, 0, tempArr, 0, length);
        System.arraycopy(indexes, 0, tempIndexes, 0, length);
        System.arraycopy(recs, 0, tempRecs, 0, length);
        for (int i = length; i < tempIndexes.length; i++) {
            tempIndexes[i] = -1;
            recs[i] = -1;
        }
        this.elements = tempArr;
        this.indexes = tempIndexes;
    }

    public void change(int index, K element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("超出限制");
        }
        this.elements[index] = element;
        int currentHeapIndex = this.recs[index];
        swam(currentHeapIndex);
        sink(currentHeapIndex);
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

        sb.append("反向索引数组是：").append(Arrays.toString(recs)).append("\n");
        sb.append("元素的数量是：").append(size);

        return sb.toString();
    }
}
