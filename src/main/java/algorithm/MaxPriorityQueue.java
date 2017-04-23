package algorithm;

/**
 * Created by jicheng on 2017/4/24.
 */
public class MaxPriorityQueue<Key extends Comparable<Key>> {

    private Object[] elements = new Object[Common.INIT_SIZE + 1];

    private int currentSize = 0;

    public MaxPriorityQueue() {
    }

    public MaxPriorityQueue(Key[] key) {

    }

    public void insert(Key key) {
        if (this.currentSize == this.elements.length - 1) {
            this.delMax();
        }
        this.elements[++this.currentSize] = key;
        if (currentSize != 1) {
            swim(this.currentSize);
        }

    }

    public Key delMax() {
        if (this.currentSize == 0) {
            return null;
        } else {
            Key result = (Key) this.elements[1];
            this.elements[1] = this.elements[this.currentSize];
            this.elements[this.currentSize--] = null;
            sink(1);
            return result;
        }
    }

    private void sink(int currentSize) {
        while (currentSize <= this.currentSize) {
            int parent = currentSize;
            int child = ((Key) this.elements[parent * 2]).compareTo((Key) this.elements[parent * 2 + 1]) < 0 ? (parent * 2 + 1) : parent * 2;
            if (((Key) this.elements[parent]).compareTo((Key) this.elements[child]) < 0) {
                swap(child, parent);
                currentSize = child * 2;
            } else {
                break;
            }
        }
    }

    private void swim(int currentSize) {
        while (currentSize >= 1) {
            int child = currentSize;
            int parent = currentSize >>> 2;
            if (((Key) this.elements[parent]).compareTo((Key) this.elements[child]) < 0) {
                swap(child, parent);
                currentSize = parent >>> 2;
            } else {
                break;
            }
        }
    }

    private void swap(int child, int parent) {
        Object temp = this.elements[child];
        this.elements[child] = this.elements[parent];
        this.elements[parent] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new MaxPriorityQueue<Integer>().elements.length);
    }

    public Key max() {
        if (this.isEmplty()) {
            return null;
        } else {
            return (Key) this.elements[1];
        }
    }

    public boolean isEmplty() {
        return this.currentSize == 0;
    }

    public int size() {
        return this.currentSize;
    }
}
