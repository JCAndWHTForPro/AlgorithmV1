package algorithm.leetcode;

/**
 * @ClassName: SolutionLeetCode206
 * @Author: jicheng
 * @CreateDate: 2019/4/17 上午12:21
 */
public class SolutionLeetCode25 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return  head;
        }

        ListNode countNode = head;
        for (int i = 0; i < k; i++) {
            if (countNode == null) {
                return head;
            }
            countNode = countNode.next;
        }

        ListNode preNode = head;
        ListNode firstNode = head.next;
        head.next = null;
        for (int i = 0; i < k-1; i++) {
            ListNode tempNode = firstNode.next;
            firstNode.next = head;
            head = firstNode;
            firstNode = tempNode;
        }
        preNode.next = reverseKGroup(firstNode,k);

        return head;
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

        ListNode listNode = new SolutionLeetCode25().reverseKGroup(n1,1);
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
