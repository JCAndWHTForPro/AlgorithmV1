package algorithm.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 给出一个字符串 s 以及 n 个子串。你可以从字符串 s 中移除 n 个子串中的任意一个，使得剩下来s的长度最小，输出这个最小长度。
 * <p>
 * 例1:
 * <p>
 * 输入:
 * "ccdaabcdbb"
 * ["ab","cd"]
 * 输出:
 * 2
 * 解释:
 * ccdaabcdbb -> ccdacdbb -> cabb -> cb (length = 2)
 * 例2:
 * <p>
 * 输入:
 * "abcabd"
 * ["ab","abcd"]
 * 输出:
 * 0
 * 解释:
 * abcabd -> abcd -> "" (length = 0)
 *
 * @ClassName: SolutionLintCode624
 * @Author: jicheng
 * @CreateDate: 2019/12/7 3:08 AM
 */
public class SolutionLintCode624 {

    /*
     * @param s: a string
     * @param dict: a set of n substrings
     * @return: the minimum length
     */
    public int minLength(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        int minLen = s.length();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                minLen = Math.min(minLen, poll.length());
                for (String str : dict) {
                    int inde = poll.indexOf(str);
//                    Pattern pattern = Pattern.compile();
                    while (inde != -1) {
                        String subString = poll.substring(0, inde) + poll.substring(inde + str.length());
                        if (!visited.contains(subString)) {
                            queue.offer(subString);
                            visited.add(subString);
                        }
                        inde = poll.indexOf(str, inde + str.length());
                    }
                }
            }
        }

        return minLen;
    }

    public static void main(String[] args) {
        SolutionLintCode624 solutionLintCode624 = new SolutionLintCode624();
        Set<String> set = new HashSet<>();
        set.add("ab");
        set.add("abcd");
        int i = solutionLintCode624.minLength("abcabd", set);
        System.out.println(i);
    }
}
