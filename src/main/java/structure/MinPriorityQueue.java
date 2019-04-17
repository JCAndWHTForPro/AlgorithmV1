package structure;

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

    public MinPriorityQueue(Comparable[] comps) {
        if (Objects.isNull(comps) || comps.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        this.elements = new Object[comps.length];
        this.size = 0;
        for (int i = 0; i < comps.length && comps[i] != null; i++) {
            this.elements[i] = comps[i];
            this.size++;
        }
        heapify();
    }

    private void heapify() {
        if (this.size == 0) {
            return;
        }
        int index = (this.size - 2) / 2;
        for (int i = index; i >= 0; i--) {
            sink(i);
        }
    }


    private void checkLength(int index) {
        if (index < 0 || index >= this.elements.length) {
            throw new IllegalArgumentException("超出限制");
        }
    }

    public int lchild(int index) {
        checkLength(index);
        return (index * 2) + 1;
    }

    public int rchild(int index) {
        checkLength(index);
        return (index * 2) + 2;
    }


    public int parent(int index) {
        checkLength(index);
        return (index - 1) / 2;
    }

    public void swap(int i, int j) {
        checkLength(i);
        checkLength(j);

        K temp = (K) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * 下沉
     */
    public void sink(int index) {
        checkLength(index);
        int p = index;
        /**
         * 如果左孩子大于或者等于当前堆中元素个数的话，
         * 表明当前节点已经是叶子节点，可以不用继续遍历了
         */
        while (p < size && lchild(p) < size) {
            K currentEle = (K) elements[p];
            // 获取左孩子索引
            int lchild = lchild(p);
            // 获取右孩子索引
            int rchild = rchild(p);
            // 获取左孩子的值
            K lelement = (K) elements[lchild];
            // 获取右孩子的值
            K relement = (K) elements[rchild];
            // 获取左右孩子最小值的那个节点索引，注意，这里右孩子有可能为空
            int minChildIndex = Objects.isNull(relement) ? lchild :
                    (lelement.compareTo(relement) > 0 ? rchild : lchild);
            // 使用当前节点值与最小值比较，如果当前值还要小，那就交换
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

        checkLength(index);

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
     */
    public K delElemet() {
        if (size == 0) {
            throw new IllegalArgumentException("当前堆已经空了");
        }
        K result = (K) elements[0];
        if (size > 1) {
            swap(0, size - 1);
            elements[--size] = null;
            sink(0);
        } else {
            size--;
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
