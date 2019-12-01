package algorithm.lintcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 这个是二叉树的层序遍历
 *
 * @ClassName: SolutionLintCode69
 * @Author: jicheng
 * @CreateDate: 2019/12/1 8:57 PM
 */
public class SolutionLintCode69 {


    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> subResult = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                subResult.add(poll.val);
                if(poll.left!=null) {
                    queue.offer(poll.left);
                }
                if(poll.right!=null) {
                    queue.offer(poll.right);
                }
            }
            result.add(subResult);

        }

        return result;

    }
}
