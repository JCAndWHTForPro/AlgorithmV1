package algorithm.lintcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个pattern和一个字符串str，查找str是否遵循相同的模式。
 * 这里遵循的意思是一个完整的匹配，在一个字母的模式和一个非空的单词str之间有一个双向连接的模式对应。
 * (如果a对应s，那么b不对应s。例如，给定的模式= "ab"， str = "ss"，返回false）。
 * <p>
 * 样例
 * 样例1
 * <p>
 * 输入:
 * pattern = "abab"
 * str = "redblueredblue"
 * 输出: true
 * 说明: "a"->"red","b"->"blue"
 * 样例2
 * <p>
 * 输入:
 * pattern = "aaaa"
 * str = "asdasdasdasd"
 * 输出: true
 * 说明: "a"->"asd"
 * 样例3
 * <p>
 * 输入:
 * pattern = "aabb"
 * str = "xyzabcxzyabc"
 * 输出: false
 * 注意事项
 * 您可以假设模式和str只包含小写字母
 *
 * @ClassName: SolutionLintCode829
 * @Author: jicheng
 * @CreateDate: 2019/12/31 11:14 PM
 */
public class SolutionLintCode829 {

    /**
     * @param pattern: a string,denote pattern string
     * @param str:     a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        char[] patternArr = pattern.toCharArray();
        Set<String> visited = new HashSet<>();
        Map<Character, String> strMap = new HashMap<>();
        return map(patternArr, 0, str, 0, visited, strMap);
    }

    private boolean map(char[] patternArr,
                        int patternIndex,
                        String str,
                        int strIndex,
                        Set<String> visited,
                        Map<Character, String> strMap) {
        if (patternIndex == patternArr.length) {
            return strIndex == str.length();
        }
        char patternChar = patternArr[patternIndex];
        if (strMap.containsKey(patternChar)) {
            String matchStr = strMap.get(patternChar);
            if (!str.startsWith(matchStr, strIndex)) {
                return false;
            }
            return map(patternArr,
                    patternIndex + 1,
                    str,
                    strIndex + matchStr.length(),
                    visited,
                    strMap);
        }
        String word = "";
        for (int i = strIndex; i < str.length(); i++) {
            word += str.charAt(i);
            if (visited.contains(word)) {
                continue;
            }
            strMap.put(patternArr[patternIndex], word);
            visited.add(word);
            if (map(patternArr, patternIndex + 1, str, i + 1, visited, strMap)) {
                return true;
            }
            strMap.remove(patternArr[patternIndex]);
            visited.remove(word);
        }

        return false;
    }

    public static void main(String[] args) {
        SolutionLintCode829 solutionLintCode829 = new SolutionLintCode829();
        boolean b = solutionLintCode829.wordPatternMatch("aabb", "xyzabcxzyabc");
        System.out.println(b);
    }
}
