package algorithm.lintcode;

import java.util.*;

/**
 * 给出一棵二叉树，返回其节点值的锯齿形层次遍历（先从左往右，下一层再从右往左，层与层之间交替进行）
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入:{1,2,3}
 * 输出:[[1],[3,2]]
 * 解释:
 * 1
 * / \
 * 2   3
 * 它将被序列化为 {1,2,3}
 * 样例 2:
 * <p>
 * 输入:{3,9,20,#,#,15,7}
 * 输出:[[3],[20,9],[15,7]]
 * 解释:
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 它将被序列化为 {3,9,20,#,#,15,7}
 * <p>
 * <p>
 * TODO jicheng 这个也好做，用stack辅助
 *
 * @ClassName: SolutionLintCode70
 * @Author: jicheng
 * @CreateDate: 2019/12/4 12:47 AM
 */
public class SolutionLintCode71 {

    /**
     * @param root: A Tree
     * @return: A list of lists of integer include the zigzag level order traversal of its nodes' values.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here

        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
        boolean reverse = false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> clist = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                int val = poll.val;
                if (!reverse) {
                    clist.add(val);
                } else {
                    stack.push(val);
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }

            }
            if(reverse){
                while (!stack.isEmpty()){
                    clist.add(stack.pop());
                }
            }
            reverse = !reverse;
            result.add(clist);
        }


        return result;
    }
}
