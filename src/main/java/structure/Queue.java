package structure;

import structure.Common;

/**
 * Created by jicheng on 2017/4/19.
 */
public class Queue<T> {

    private Object[] elements;

    private int begin = 0;

    private int end = 0;

    private int size = Common.INIT_SIZE;

    public Queue() {
        this.elements = new Object[this.size];
    }

    public Queue(int size) {
        this.size = size;
        this.elements = new Object[this.size];
    }

    public boolean isEmpty() {
        return this.begin == this.end;
    }

    public boolean isFull() {
        return (this.end + 1) % this.size == this.begin;
    }

    public void ensureLarge() {
        if (this.isFull()) {
            Object[] newElements = new Object[this.size + Common.GROW_SIZE];
            int newEnd = 0;
            while (!this.isEmpty()) {
                T data = this.dequeue();
                newElements[newEnd++] = data;
            }
            this.begin = 0;
            this.end = newEnd;
            this.size = newElements.length;
            this.elements = newElements;
        }
    }
    public int size(){
         if(this.end<this.begin){
             return this.end+this.size-this.begin;
         }else{
             return this.end-this.begin;
         }
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (this.isEmpty()) {
            return null;
        } else {
            T returndata = (T) this.elements[this.begin];
            this.begin = (this.begin + 1) % this.size;
            return returndata;
        }
    }

    public void enqueue(T elem) {
        this.ensureLarge();
        this.elements[this.end] = elem;
        this.end = (this.end + 1) % this.size;
    }
}
