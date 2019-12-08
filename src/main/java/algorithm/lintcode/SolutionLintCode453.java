package algorithm.lintcode;

import java.util.List;

/**
 * @ClassName: SolutionLintCode453
 * @Author: jicheng
 * @CreateDate: 2019/12/8 7:04 PM
 */
public class SolutionLintCode453 {

    private class ResultType {

        public TreeNode fistSubNode;

        public TreeNode lastSubNode;

        public ResultType(TreeNode fistSubNode, TreeNode lastSubNode) {
            this.fistSubNode = fistSubNode;
            this.lastSubNode = lastSubNode;
        }
    }

    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) {
            return;

        }
        ResultType falttenn = falttenn(root);
        root = falttenn.lastSubNode;
    }

    private ResultType falttenn(TreeNode root) {
        if (root == null) {
            return null;
        }
        ResultType leftResult = falttenn(root.left);
        ResultType rightResult = falttenn(root.right);

        if (leftResult != null && rightResult != null) {
            TreeNode tmpRight = root.right;
            root.right = leftResult.fistSubNode;
            leftResult.lastSubNode.right = tmpRight;
            root.left = null;
            return new ResultType(root, rightResult.lastSubNode);

        }
        if(rightResult == null&& leftResult != null){
            root.left = null;
            root.right = leftResult.fistSubNode;
            return new ResultType(root,leftResult.lastSubNode);
        }
        if(leftResult == null && rightResult!=null){
            root.left = null;
            root.right = rightResult.fistSubNode;
            return new ResultType(root,rightResult.lastSubNode);
        }
        return new ResultType(root, root);


    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        TreeNode leftNode = new TreeNode(4);
        TreeNode rightNode = new TreeNode(1);
        treeNode.left = leftNode;
        treeNode.right = rightNode;
        SolutionLintCode453 solutionLintCode596 = new SolutionLintCode453();
        solutionLintCode596.flatten(treeNode);
//        System.out.println(strings);
    }
}
