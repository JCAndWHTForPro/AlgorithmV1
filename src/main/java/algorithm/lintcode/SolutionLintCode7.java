package algorithm.lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 设计一个算法，并编写代码来序列化和反序列化二叉树。将树写入一个文件被称为“序列化”，读取文件后重建同样的二叉树被称为“反序列化”。
 *
 * 如何反序列化或序列化二叉树是没有限制的，你只需要确保可以将二叉树序列化为一个字符串，并且可以将字符串反序列化为原来的树结构。
 * 输入：{3,9,20,#,#,15,7}
 * 输出：{3,9,20,#,#,15,7}
 * 解释：
 * 二叉树 {3,9,20,#,#,15,7}，表示如下的树结构：
 * 	  3
 * 	 / \
 * 	9  20
 * 	  /  \
 * 	 15   7
 * 它将被序列化为 {3,9,20,#,#,15,7}
 * @ClassName: SolutionLintCode69
 * @Author: jicheng
 * @CreateDate: 2019/12/1 8:57 PM
 */
public class SolutionLintCode7 {


    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if(root == null){
            return null;
        }

        StringBuffer sb = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll != null) {
                sb.append(poll.val + ",");
            } else {
                sb.append("#,");
                continue;
            }
            queue.offer(poll.left);
            queue.offer(poll.right);
        }
        int lastIndex = getStringLastIndex(sb);

        return "{"+sb.substring(0, lastIndex + 1)+"}";
    }

    private int getStringLastIndex(StringBuffer sb) {
        int length = sb.length() - 1;
        while (length >= 0) {
            char c = sb.charAt(length);
            if (c >= '0' && c <= '9') {
                break;
            }
            length--;
        }
        return length;
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if(data == null || "".equals(data)){
            return null;
        }
        String substring = data.substring(1, data.length() - 1);
        if("".equals(substring)){
            return null;
        }
        String[] strings = substring.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(strings[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (index < strings.length) {
            TreeNode poll = queue.poll();
            String left = strings[index];
            if(left.matches("\\d+")){
                TreeNode left1 = new TreeNode(Integer.valueOf(left));
                poll.left = left1;
                queue.offer(left1);
            }
            if((++index)>=strings.length){
                break;
            }
            String right = strings[index++];
            if(right.matches("\\d+")){
                TreeNode right1 = new TreeNode(Integer.valueOf(right));
                poll.right = right1;
                queue.offer(right1);
            }

        }
        return root;
    }

    public static void main(String[] args) {
        String str = "{3,9,20,#,#,15,7}";
        TreeNode deserialize = new SolutionLintCode7().deserialize(str);
        System.out.println(deserialize);
    }
}
