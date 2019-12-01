package algorithm.lintcode;

import sun.font.CharToGlyphMapper;

import java.util.*;

/**
 * 单词接龙问题：
 * 给出两个单词（start和end）和一个字典，找出从start到end的最短转换序列，输出最短序列的长度。
 * <p>
 * 变换规则如下：
 * <p>
 * 每次只能改变一个字母。
 * 变换过程中的中间单词必须在字典中出现。(起始单词和结束单词不需要出现在字典中)
 * <p>
 * 注意事项
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * @ClassName: SolutionLintCode120
 * @Author: jicheng
 * @CreateDate: 2019/12/2 1:40 AM
 */
public class SolutionLintCode120 {

    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null || dict.isEmpty()) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int maxLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                String poll = queue.poll();
                for (int i = 0; i < poll.length(); i++) {
                    char[] chars = poll.toCharArray();
                    char currentChar = chars[i];
                    for (char changeChar = 'a'; changeChar <= 'z'; changeChar++) {
                        if (changeChar == currentChar) {
                            continue;
                        }
                        chars[i] = changeChar;
                        String anObject = new String(chars);
                        if (end.equals(anObject)) {
                            return maxLen + 1;
                        }
                        if (dict.contains(anObject)) {
                            queue.offer(anObject);
                            dict.remove(anObject);
                        }

                    }
                }
            }
            maxLen++;
        }

        return 0;
    }

    public static void main(String[] args) {
        SolutionLintCode120 solutionLintCode120 = new SolutionLintCode120();

        int length = solutionLintCode120.ladderLength("a", "c", new HashSet<>(Arrays.asList("a", "b", "c")));

        System.out.println(length);
    }
}
