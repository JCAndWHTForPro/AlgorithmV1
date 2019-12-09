package algorithm.lintcode;

/**
 * 给定一棵二叉树，找到两个节点的最近公共父节点(LCA)。
 * <p>
 * 最近公共祖先是两个节点的公共的祖先节点且具有最大深度。
 * <p>
 * 假设给出的两个节点都在树中存在
 *
 * @ClassName: SolutionLintCode88
 * @Author: jicheng
 * @CreateDate: 2019/12/8 9:09 PM
 */
public class SolutionLintCode88 {

//    private class ResultType{
//        public
//    }

    /*
     * @param root: The root of the binary search tree.
     * @param A: A TreeNode in a Binary.
     * @param B: A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null) {
            return null;
        }
        if (root == A) {
            return A;
        }
        if (root == B) {
            return B;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, A, B);
        TreeNode rightNode = lowestCommonAncestor(root.right, A, B);
        if (leftNode != null && rightNode != null) {
            return root;
        }
        if (leftNode != null) {
            return leftNode;
        }
        if (rightNode != null) {
            return rightNode;
        }
        return null;
    }
}
