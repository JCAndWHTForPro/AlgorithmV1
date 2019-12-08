package algorithm.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: SolutionLintCode480
 * @Author: jicheng
 * @CreateDate: 2019/12/8 6:33 PM
 */
public class SolutionLintCode480 {

    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.left == null && root.right == null) {
            return Arrays.asList(String.valueOf(root.val));
        }
        List<String> resultList = new ArrayList<>();

        binaryTreePaths(root, resultList, null);
        return resultList;

    }

    private void binaryTreePaths(TreeNode root, List<String> resultList, String s) {
        if (s == null) {
            s = String.valueOf(root.val);
        } else {
            s = s + "->" + root.val;
        }
        if (root.left == null && root.right == null) {
            resultList.add(s);
            return;
        }
        if (root.left != null) {
            binaryTreePaths(root.left, resultList, s);
        }
        if (root.right != null) {
            binaryTreePaths(root.right, resultList, s);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        TreeNode leftNode = new TreeNode(4);
        TreeNode rightNode = new TreeNode(1);
        treeNode.left = leftNode;
        treeNode.right = rightNode;
        SolutionLintCode480 solutionLintCode596 = new SolutionLintCode480();
        List<String> strings = solutionLintCode596.binaryTreePaths(treeNode);
        System.out.println(strings);
    }
}
