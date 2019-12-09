package algorithm.lintcode;

/**
 * 给定一个二叉树，判断它是否是合法的二叉查找树(BST)
 * <p>
 * 一棵BST定义为：
 * <p>
 * 节点的左子树中的值要严格小于该节点的值。
 * 节点的右子树中的值要严格大于该节点的值。
 * 左右子树也必须是二叉查找树。
 * 一个节点的树也是二叉查找树。
 *
 * @ClassName: SolutionLintCode95
 * @Author: jicheng
 * @CreateDate: 2019/12/8 10:27 PM
 */
public class SolutionLintCode95 {


    private class Result {
        public boolean isBST;
        public TreeNode minNode;
        public TreeNode maxNode;

        public Result(boolean isBST, TreeNode minNode, TreeNode maxNode) {
            this.isBST = isBST;
            this.minNode = minNode;
            this.maxNode = maxNode;
        }
    }

    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }

        Result result = ivb(root);
        return result.isBST;
    }

    private Result ivb(TreeNode root) {
        if (root == null) {
            return new Result(true, null, null);
        }
        Result lr = ivb(root.left);
        Result rr = ivb(root.right);
        if (!lr.isBST || !rr.isBST) {
            return new Result(false, null, null);
        }

        Result r = new Result(true, root, root);
        if (lr.maxNode != null) {
            if (lr.maxNode.val >= root.val) {
                r.isBST = false;
            } else {
                r.minNode = lr.minNode;
            }
        }
        if (rr.minNode != null) {
            if (rr.minNode.val <= root.val) {
                r.isBST = false;
            } else {
                r.maxNode = rr.maxNode;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(10);
        TreeNode leftNode = new TreeNode(5);

        TreeNode lleftNode = new TreeNode(1);
        TreeNode lrightNode = new TreeNode(100);
        treeNode.left = leftNode;
        leftNode.left = lleftNode;
        leftNode.right = lrightNode;
        SolutionLintCode95 solutionLintCode596 = new SolutionLintCode95();
        boolean validBST = solutionLintCode596.isValidBST(treeNode);
        System.out.println(validBST);
    }
}
