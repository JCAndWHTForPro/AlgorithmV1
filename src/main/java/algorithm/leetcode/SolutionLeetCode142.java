package algorithm.leetcode;

/**
 * @ClassName: SolutionLeetCode206
 * @Author: jicheng
 * @CreateDate: 2019/4/17 上午12:21
 */
public class SolutionLeetCode142 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     * 比较好的理解：
     * 快慢指针法，fast每次向前2，slow每次向前1。
     * 如果某一个到了NULL则说明没有环。如果有环就必然某一次fast和slow相等。
     * 注意，fast走过步数始终等于slow走过步数的两倍，
     * 假设slow走过的步数是step（后来发现这个step的值在程序里是不需要计算的），
     * 那么fast比slow多走过的步数也等于step。然后又因为它们现在在同一个地方，
     * 也就是fast比slow多走了若干圈，也就是说step等于环的长度的整数倍，
     * 其实我们也不用关心到底是多少倍了。
     * 环内结点的标志（充要条件）是：从此结点前进step步，能够回到此结点。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        boolean isCycle = false;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                isCycle = true;
                break;
            }
            slow = slow.next;
            if (fast.next == null) {
                break;
            }
            fast = fast.next.next;
        }
        if (isCycle) {
            while (head != slow) {
                head = head.next;
                slow = slow.next;
            }
            return head;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n3;

        ListNode listNode = new SolutionLeetCode142().detectCycle(n1);
        System.out.println(listNode);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
