package algorithm.geektime;

/**
 * 求链表的中间结点
 *
 * @ClassName: Alm05
 * @Author: jicheng
 * @CreateDate: 2019/11/29 11:37 PM
 */
public class Alm05 {

    /**
     * 快慢指针（双倍）
     * 如果是双数节点返回null
     *
     * @param head
     * @return
     */
    public Node solution01(Node head) {

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (true) {
            fast = fast.next;
            if(fast.next == null){
                break;
            }
            fast = fast.next;
            slow = slow.next;
            if(fast.next == null){
                return slow;
            }
        }


        return null;

    }
}
