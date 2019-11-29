package algorithm.geektime;

import java.util.HashMap;
import java.util.Map;

/**
 * 链表中环的检测
 *
 * @ClassName: Alm03
 * @Author: jicheng
 * @CreateDate: 2019/11/29 10:41 PM
 */
public class Alm03 {

    /**
     * 使用O(n)空间复杂度的方法
     *
     * @param head
     * @return
     */
    public boolean solutions(Node head) {
        if (head == null) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();

        map.put(head.hashCode(), 1);
        Node tmpNode = head.next;
        while (tmpNode != null) {
            int key = tmpNode.hashCode();
            if (map.get(key) != null) {
                return true;
            }
            map.put(key, 1);
            tmpNode = tmpNode.next;
        }


        return false;
    }


    /**
     * 使用快慢指针解决
     *
     * @param head
     * @return
     */
    public boolean solution02(Node head) {
        if (head == null) {
            return false;
        }

        Node slow = head;
        Node fast = head.next;
        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                return false;
            }

            fast = fast.next;
            if (fast == null) {
                return false;
            }
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Node<String> n8 = new Node<>("129", null);
        Node<String> n7 = new Node<>("128", n8);
        Node<String> n6 = new Node<>("127", n7);
        Node<String> n5 = new Node<>("126", n6);
        Node<String> n4 = new Node<>("125", n5);
        Node<String> n3 = new Node<>("124", n4);
        Node<String> n2 = new Node<>("123", n3);
        Node<String> n1 = new Node<>("12", n2);

        n8.setNext(n4);

        boolean solutions = new Alm03().solution02(n1);
        System.out.println(solutions);
    }
}
