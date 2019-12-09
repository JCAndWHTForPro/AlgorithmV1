package algorithm.lintcode;

/**
 * @ClassName: SolutionLintCode902
 * @Author: jicheng
 * @CreateDate: 2019/12/8 8:51 PM
 */
public class SolutionLintCode902 {

    private int currentIndex = 0;

    /**
     * @param root: the given BST
     * @param k:    the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        // write your code here
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int r = kthSmallest(root.left, k);
        if (currentIndex == k - 1 && r != Integer.MIN_VALUE) {
            return r;
        }
        if (currentIndex < k - 1) {
            currentIndex++;
        } else {
            return root.val;
        }

        return kthSmallest(root.right, k);

    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        TreeNode leftNode = new TreeNode(1);
        TreeNode rightNode = new TreeNode(3);
        treeNode.left = leftNode;
        treeNode.right = rightNode;
        SolutionLintCode902 solutionLintCode596 = new SolutionLintCode902();
        int i = solutionLintCode596.kthSmallest(treeNode, 1);
        System.out.println(i);
        //        System.out.println(subtree);
    }

}
