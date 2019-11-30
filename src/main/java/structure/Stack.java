package structure;

/**
 * Created by jicheng on 2017/4/19.
 */
public class Stack<T> {


    private Object[] elements;

    private int top = -1;

    private int size = Constants.INIT_SIZE;

    public Stack() {
        this.elements = new Object[this.size];
    }

    public Stack(int size) {
        this.size = size;
        this.elements = new Object[this.size];
    }

    public int size() {
        return this.top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void ensureLarge() {
        if (this.size() == this.size) {
            Object[] newElements = new Object[this.size() + Constants.GROW_SIZE];
            System.arraycopy(this.elements, 0, newElements, 0, this.size());
            this.elements = newElements;
            this.size = newElements.length;
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) {
            return null;
        } else {
            T element = (T) elements[top];
            elements[top] = null;
            top--;
            return element;
        }
    }

    public void push(T element) {
        this.ensureLarge();
        this.elements[++top] = element;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (top != -1 && index <= top && index >= 0) {
            return (T) this.elements[index];
        }
        return null;
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public T peek() {
        if (top == -1) {
            return null;
        }
        return (T) this.elements[top];
    }

    public boolean find(T elem) {
        for (int i = 0; i <= top; i++) {
            if (this.elements[i].equals(elem)) {
                return true;
            }
        }
        return false;
    }

    public int indexof(T elem) {
        for (int i = 0; i <= top; i++) {
            if (this.elements[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }
}
