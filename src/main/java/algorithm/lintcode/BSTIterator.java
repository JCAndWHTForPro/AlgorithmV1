package algorithm.lintcode;

import java.util.Stack;

/**
 * lintcode86题；
 * <p>
 * 设计实现一个带有下列属性的二叉查找树的迭代器：
 * next()返回BST中下一个最小的元素
 * <p>
 * 元素按照递增的顺序被访问（比如中序遍历）
 * next()和hasNext()的询问操作要求均摊时间复杂度是O(1)
 * 样例 1:
 * <p>
 * 输入：{10,1,11,#,6,#,12}
 * 输出：[1, 6, 10, 11, 12]
 * 解释：
 * 二叉查找树如下 :
 * 10
 * /\
 * 1 11
 * \  \
 * 6  12
 * 可以返回二叉查找树的中序遍历 [1, 6, 10, 11, 12]
 * 样例 2:
 * <p>
 * 输入：{2,1,3}
 * 输出：[1,2,3]
 * 解释：
 * 二叉查找树如下 :
 * 2
 * / \
 * 1   3
 * 可以返回二叉查找树的中序遍历 [1,2,3]
 *
 * @ClassName: SolutionLintCode86
 * @Author: jicheng
 * @CreateDate: 2019/12/9 4:14 PM
 */
public class BSTIterator {

    private Stack<TreeNode> stack;

    /*
     * @param root: The root of binary tree.
     */
    public BSTIterator(TreeNode root) {
        // do intialization if necessary
        this.stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return !this.stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        // write your code here
        TreeNode result = this.stack.peek();

        if (result.right != null) {
            TreeNode right = result.right;
            while (right != null) {
                this.stack.push(right);
                right = right.left;
            }
        } else {
            TreeNode pop = this.stack.pop();
            while(!this.stack.isEmpty() && this.stack.peek().right == pop){
                pop = this.stack.pop();
            }

        }

        return result;
    }
}
