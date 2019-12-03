package algorithm.lintcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: SolutionLintCode242
 * @Author: jicheng
 * @CreateDate: 2019/12/3 9:52 PM
 */
public class SolutionLintCode242 {

    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        // Write your code here
        if (root == null) {
            return new ArrayList<>();
        }

        List<ListNode> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode fist = null, current = null;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (current == null) {
                    current = new ListNode(poll.val);
                } else {
                    current.next = new ListNode(poll.val);
                    current = current.next;
                }
                if (fist == null) {
                    fist = current;
                }

                if (poll.left != null) {
                    queue.offer(poll.left);

                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }


            }
            result.add(fist);
        }

        return result;
    }
}
