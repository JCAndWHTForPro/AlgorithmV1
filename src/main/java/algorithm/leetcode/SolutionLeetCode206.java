package algorithm.leetcode;

import java.util.LinkedHashSet;

/**
 * @ClassName: SolutionLeetCode206
 * @Author: jicheng
 * @CreateDate: 2019/4/17 上午12:21
 */
public class SolutionLeetCode206 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode fist = head;
        ListNode second = head.next;
        while (second != null) {
            if(fist == head){
                fist.next = null;
            }
            ListNode nextTmp = second.next;
            second.next = fist;
            fist = second;
            second = nextTmp;
        }
        return fist;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
