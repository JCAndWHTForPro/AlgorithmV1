package algorithm.leetcode;

/**
 * @ClassName: SolutionLeetCode206
 * @Author: jicheng
 * @CreateDate: 2019/4/17 上午12:21
 */
public class SolutionLeetCode141 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode nnNode = head.next.next;
        ListNode nNode = head;
        while (nnNode != null) {
            if (nNode == nnNode) {
                return true;
            }
            if (nnNode.next == null) {
                return false;
            }
            nNode = nNode.next;
            nnNode = nnNode.next.next;

        }

        return false;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
