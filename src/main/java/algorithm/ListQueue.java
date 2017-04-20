package algorithm;

/**
 * Created by 10192096 on 2017/4/20 0020.
 */
public class ListQueue<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node begin;
    private Node end;

    public ListQueue() {
        begin = null;
        end = null;
    }

    public boolean isEmpty() {
        return begin == null;
    }
    public int size(){
        if(this.isEmpty()){
            return 0;
        }
        Node tempBegin = this.begin;
        Node tempEnd = this.end;
        int tempSize = 0;
        while (tempBegin!=tempEnd.next){
            tempSize++;
            tempBegin = tempBegin.next;
        }
        return tempSize;
    }
    public void enqueue(T data){
        Node dataNode = new Node(data,null);
        if(this.isEmpty()){
            this.begin = this.end = dataNode;
        }else{
            this.end.next = dataNode;
            this.end = dataNode;
        }
    }
    @SuppressWarnings("ConstantConditions")
    public T dequeue(){
        if(this.isEmpty()){
            return null;
        }else{
            Node tempBegin = this.begin;
            T returndata = this.begin.data;
            this.begin = this.begin.next;
            tempBegin.next= null;
            if(this.begin == null){
                this.end = this.begin;
            }
            return returndata;
        }
    }
}
