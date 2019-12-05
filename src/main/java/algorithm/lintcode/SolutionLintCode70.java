package algorithm.lintcode;

import java.util.*;

/**
 * 给出一棵二叉树，返回其节点值从底向上的层次序遍历（按从叶节点所在层到根节点所在的层遍历，然后逐层从左往右遍历）
 * <p>
 * 例1:
 * <p>
 * 输入:
 * {1,2,3}
 * 输出:
 * [[2,3],[1]]
 * 解释:
 * 1
 * / \
 * 2   3
 * 它将被序列化为 {1,2,3}
 * 层次遍历
 * 例2:
 * <p>
 * 输入:
 * {3,9,20,#,#,15,7}
 * 输出:
 * [[15,7],[9,20],[3]]
 * 解释:
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 它将被序列化为 {3,9,20,#,#,15,7}
 * 层次遍历
 * <p>
 * TODO jicheng 这个也好做，用stack辅助
 *
 * @ClassName: SolutionLintCode70
 * @Author: jicheng
 * @CreateDate: 2019/12/4 12:47 AM
 */
public class SolutionLintCode70 {

    /**
     * @param root: A tree
     * @return: buttom-up level order a list of lists of integer
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {


        if (root == null) {
            return new ArrayList<>();
        }
        Stack<List<Integer>> stack = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> clist = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                clist.add(poll.val);
                if(poll.left!=null){
                    queue.offer(poll.left);
                }
                if(poll.right!=null){
                    queue.offer(poll.right);
                }
            }
            stack.push(clist);
        }
        while(!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        treeNode.left = t1;
        treeNode.right = t2;
        SolutionLintCode70 solutionLintCode70 = new SolutionLintCode70();
        List<List<Integer>> lists = solutionLintCode70.levelOrderBottom(treeNode);
        System.out.println(lists);
    }
}
