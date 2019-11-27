package algorithm.geektime;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @ClassName: LRUCache
 * @Author: jicheng
 * @CreateDate: 2019/11/28 12:51 AM
 */
public class LRUCache<V> {

    private HashMap<V, Node> hashMap;

    private Node head;

    private Node last;

    private int maxLen;

    private int currentSize;


    private class Node {
        public V data;
        public Node pre;
        public Node next;

        public Node(V data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }


    }

    public LRUCache(int maxLen) {
        this.maxLen = maxLen;
        this.currentSize = 0;
        hashMap = new HashMap<>();
    }


    public void addValue(V data) {

        Node node = new Node(data, null, null);
        if (this.head == this.last && this.head == null) {
            this.head = this.last = node;
            return;
        }
        addHead(data, node);


    }

    private void addHead(V data, Node node) {
        node.next = this.head;
        this.head.pre = node;
        this.head = node;
        node.pre = null;
        this.hashMap.put(data, node);
        this.currentSize++;
        if (this.currentSize > this.maxLen) {
            Node temp = this.last;
            this.last = this.last.pre;
            if (this.last != null) {
                this.last.next = null;
            }
            this.hashMap.remove(temp.data);
            this.currentSize--;


        }
    }

    public Node getNode(V data) {
        Node node = this.hashMap.get(data);
        if (node != null) {
            if (this.head == node) {
                return this.head;
            }
            Node pre = node.pre;
            Node next = node.next;
            if (pre != null) {
                pre.next = next;
            }
            if (next != null) {
                next.pre = pre;
            }
            if (this.last == node) {
                this.last = pre;
            }
            this.addHead(node.data, node);

        }

        return node;

    }

}
