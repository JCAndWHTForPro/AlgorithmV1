package algorithm.lintcode;

/**
 * @ClassName: SolutionLintCode93
 * @Author: jicheng
 * @CreateDate: 2019/12/8 8:27 PM
 */
public class SolutionLintCode93 {


    private class ResultType {

        public boolean isBalance;

        public int high;

        public ResultType(boolean isBalance, int high) {
            this.isBalance = isBalance;
            this.high = high;
        }
    }

    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // write your code here
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        ResultType resultType = balec(root);
        return resultType.isBalance;
    }

    private ResultType balec(TreeNode root) {
        if (root == null) {
            return new ResultType(true, 0);
        }
        ResultType leftResult = balec(root.left);
        ResultType rightResult = balec(root.right);
        if (!leftResult.isBalance || !rightResult.isBalance) {
            return new ResultType(false, -1);
        }
        if (Math.abs(leftResult.high - rightResult.high) > 1) {
            return new ResultType(false, -1);
        }
        return new ResultType(true, Math.max(leftResult.high, rightResult.high) + 1);
    }
}
