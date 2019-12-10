package algorithm.lintcode;

import java.util.*;

/**
 * 给定一棵非空二叉搜索树以及一个target值，找到 BST 中最接近给定值的 k 个数。
 * <p>
 * 样例
 * 样例 1:
 * <p>
 * 输入:
 * {1}
 * 0.000000
 * 1
 * 输出:
 * [1]
 * 解释：
 * 二叉树 {1}，表示如下的树结构：
 * 1
 * 样例 2:
 * <p>
 * 输入:
 * {3,1,4,#,2}
 * 0.275000
 * 2
 * 输出:
 * [1,2]
 * 解释：
 * 二叉树 {3,1,4,#,2}，表示如下的树结构：
 * 3
 * /  \
 * 1    4
 * \
 * 2
 * 挑战
 * 假设是一棵平衡二叉搜索树，你可以用时间复杂度低于O(n)的算法解决问题吗( n 为节点个数)？
 * <p>
 * 注意事项
 * 给出的target值为浮点数
 * 你可以假设 k 总是合理的，即 k ≤ 总节点数
 * 我们可以保证给出的 BST 中只有唯一一个最接近给定值的 k 个值的集合
 *
 * @ClassName: SolutionLintCode901
 * @Author: jicheng
 * @CreateDate: 2019/12/9 9:04 PM
 */
public class SolutionLintCode901 {
    /**
     * @param root:   the given BST
     * @param target: the given target
     * @param k:      the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
        if (root == null) {
            return Collections.emptyList();
        }
        if (root.left == null && root.right == null) {
            return Arrays.asList(root.val);
        }
        List<Integer> resultList = new ArrayList<>();

        // 这个是存储比目标节点小的值
        Stack<TreeNode> lowerStack = getLowerStack(root, target);
        // 这个是存储比目标节点大的值
        Stack<TreeNode> upperStack = new Stack<>();
        upperStack.addAll(lowerStack);

        if (lowerStack.peek().val > target) {
            // 栈顶元素大于目标值，表示，找到了大于目标值且是大于目标值中，最小的那一个
            removeLower(lowerStack);
        } else {
            // 这种情况和上面相反
            removeUpper(upperStack);
        }

        for (int i = 0; i < k; i++) {
            if (lowerStack.isEmpty() ||
                    (!upperStack.isEmpty() && upperStack.peek().val - target < target - lowerStack.peek().val)) {
                resultList.add(upperStack.peek().val);
                removeUpper(upperStack);
            }else{
                resultList.add(lowerStack.peek().val);
                removeLower(lowerStack);
            }
        }

        return resultList;
    }

    private void removeUpper(Stack<TreeNode> upperStack) {
        TreeNode top = upperStack.peek();
        if (top.right == null) {
            TreeNode pop = upperStack.pop();
            while (!upperStack.isEmpty() && upperStack.peek().right == pop) {
                pop = upperStack.pop();
            }
        } else {
            top = top.right;
            while (top != null) {
                upperStack.push(top);
                top = top.left;
            }
        }

    }

    private void removeLower(Stack<TreeNode> lowerStack) {
        TreeNode top = lowerStack.peek();
        if (top.left == null) {
            TreeNode pop = lowerStack.pop();
            while (!lowerStack.isEmpty() && lowerStack.peek().left == pop) {
                pop = lowerStack.pop();
            }
        } else {
            top = top.left;
            while (top != null) {
                lowerStack.push(top);
                top = top.right;
            }
        }

    }

    private Stack<TreeNode> getLowerStack(TreeNode root, double taeget) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            if (taeget > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return stack;
    }

}
