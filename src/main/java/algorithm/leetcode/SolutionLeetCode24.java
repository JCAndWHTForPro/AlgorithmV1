package algorithm.leetcode;

/**
 * @ClassName: SolutionLeetCode206
 * @Author: jicheng
 * @CreateDate: 2019/4/17 上午12:21
 */
public class SolutionLeetCode24 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = head.next;
        ListNode first = head;
        ListNode second = head.next;
        while (second != null) {
            ListNode next = second.next;
            ListNode nNext = null;
            if (next != null && next.next != null) {
                nNext = next.next;
            }
            second.next = first;
            if (nNext == null) {
                first.next = next;
            } else {
                first.next = nNext;
            }
            first = next;
            second = nNext;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
//        ListNode n2 = new ListNode(2);
//        ListNode n3 = new ListNode(3);
//        ListNode n4 = new ListNode(4);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;

        ListNode listNode = new SolutionLeetCode24().swapPairs(n1);
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
