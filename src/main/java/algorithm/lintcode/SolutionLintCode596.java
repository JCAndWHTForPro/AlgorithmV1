package algorithm.lintcode;

/**
 * 求最小z子树
 *
 * @ClassName: SolutionLintCode596
 * @Author: jicheng
 * @CreateDate: 2019/12/8 5:26 PM
 */
public class SolutionLintCode596 {

    private static class ResultType {

        int currentSum;

        int minSum;

        TreeNode minNode;

        public ResultType(int currentSum, int minSum, TreeNode currentNode) {
            this.currentSum = currentSum;
            this.minNode = currentNode;
            this.minSum = minSum;
        }
    }


    public TreeNode findSubtree(TreeNode root) {
        if (root.right == null && root.left == null) {
            return root;
        }
        ResultType resultType = findMinSum(root);
        return resultType.minNode;
    }

    private ResultType findMinSum(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, null);
        }
        ResultType leftr = findMinSum(root.left);
        ResultType rightr = findMinSum(root.right);
        int currentSum = leftr.currentSum + rightr.currentSum + root.val;
        int minSum = currentSum;
        TreeNode node = root;
        if (leftr.minNode != null && leftr.minSum < minSum) {
            minSum = leftr.minSum;
            node = leftr.minNode;
        }
        if (rightr.minNode != null && rightr.minSum < minSum) {
            minSum = rightr.minSum;
            node = rightr.minNode;
        }
        return new ResultType(currentSum, minSum, node);
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        TreeNode leftNode = new TreeNode(4);
        TreeNode rightNode = new TreeNode(1);
        treeNode.left = leftNode;
        treeNode.right = rightNode;
        SolutionLintCode596 solutionLintCode596 = new SolutionLintCode596();
        TreeNode subtree = solutionLintCode596.findSubtree(treeNode);
        System.out.println(subtree);
    }
}
