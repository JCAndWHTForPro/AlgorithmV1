package algorithm.lintcode;

/**
 * 给一棵非空二叉搜索树以及一个target值，找到在BST中最接近给定值的节点值
 *
 * @ClassName: SolutionLintCode900
 * @Author: jicheng
 * @CreateDate: 2019/12/8 6:14 PM
 */
public class SolutionLintCode900 {


    private class ResultType {

        public double minDistance;

        public TreeNode minNode;

        public ResultType(double minDistance, TreeNode minNode) {
            this.minDistance = minDistance;
            this.minNode = minNode;
        }
    }

    /**
     * @param root:   the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        if (root.left == null && root.right == null) {
            return root.val;
        }
        ResultType resultType = cvalue(root, target);
        return resultType.minNode.val;
    }

    private ResultType cvalue(TreeNode root, double target) {
        if (root == null) {
            return new ResultType(Double.MAX_VALUE, null);
        }
        ResultType leftType = cvalue(root.left, target);
        ResultType rightType = cvalue(root.right, target);

        double mind = leftType.minDistance < rightType.minDistance ? leftType.minDistance : rightType.minDistance;
        TreeNode rn = leftType.minDistance < rightType.minDistance ? leftType.minNode : rightType.minNode;

        if (Math.abs(target - root.val) < mind) {
            mind = Math.abs(target - root.val);
            rn = root;
        }

        return new ResultType(mind, rn);
    }
}
