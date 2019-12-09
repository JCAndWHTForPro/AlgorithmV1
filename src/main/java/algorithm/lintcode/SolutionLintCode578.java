package algorithm.lintcode;

/**
 * @ClassName: SolutionLintCode578
 * @Author: jicheng
 * @CreateDate: 2019/12/8 9:39 PM
 */
public class SolutionLintCode578 {

    private class Result {
        public TreeNode reusltNode;

        public boolean finda;

        public boolean findb;

        public Result(TreeNode reusltNode, boolean finda, boolean findb) {
            this.reusltNode = reusltNode;
            this.finda = finda;
            this.findb = findb;
        }
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null) {
            return null;
        }
        Result result = lca3(root, A, B);
        return result.reusltNode;
    }

    private Result lca3(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new Result(null, false, false);
        }
        Result result = new Result(null, false, false);
        if (root == a) {
            result.finda = true;
        }
        if (root == b) {
            result.findb = true;
        }

        Result lr = lca3(root.left, a, b);
        Result rr = lca3(root.right, a, b);
        if (lr.reusltNode != null) {
            result.reusltNode = lr.reusltNode;
            result.finda = true;
            result.findb = true;
        } else if (rr.reusltNode != null) {
            result.reusltNode = rr.reusltNode;
            result.finda = true;
            result.findb = true;
        } else if (lr.finda && rr.findb) {
            result.reusltNode = root;
            result.finda = true;
            result.findb = true;
        } else {
            result.finda = result.finda || lr.finda || rr.finda;
            result.findb = result.findb || lr.findb || rr.findb;
            if(result.findb && result.finda){
                result.reusltNode = root;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        TreeNode leftNode = new TreeNode(-1);
//        TreeNode rightNode = new TreeNode(3);
        treeNode.left = leftNode;
//        treeNode.right = rightNode;
        SolutionLintCode578 solutionLintCode596 = new SolutionLintCode578();
        TreeNode treeNode1 = solutionLintCode596.lowestCommonAncestor3(treeNode, leftNode, treeNode);
        System.out.println(treeNode1);
        //        System.out.println(subtree);
    }
}
