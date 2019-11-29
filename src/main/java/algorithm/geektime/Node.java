package algorithm.geektime;

/**
 * @ClassName: Node
 * @Author: jicheng
 * @CreateDate: 2019/11/29 10:41 PM
 */
public class Node<V> {

    public V data;

    public Node<V> next;

    public Node(V data, Node<V> next) {
        this.data = data;
        this.next = next;
    }


    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public Node<V> getNext() {
        return next;
    }

    public void setNext(Node<V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        if (this.data == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(this.data);
        Node temp = this.next;
        while (temp != null) {
            sb.append("--->").append(temp.data);
            temp = temp.next;
        }
        return sb.toString();
    }
}
