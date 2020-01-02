package algorithm.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SolutionLintCode680
 * @Author: jicheng
 * @CreateDate: 2019/12/31 4:02 PM
 */
public class SolutionLintCode680 {

    /*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        char[] chars = s.toCharArray();

        dfs(chars, 0, new ArrayList<>(), result);

        return result;
    }

    private void dfs(char[] chars,
                     int startIndex,
                     List<String> subStr,
                     List<List<String>> result) {
        if (startIndex == chars.length) {
            result.add(new ArrayList<>(subStr));
            return;
        }


        String subs = "" + chars[startIndex];
        subStr.add(subs);

        dfs(chars, startIndex + 1, subStr, result);
        subStr.remove(subStr.size() - 1);
        if (startIndex < chars.length - 1) {
            subs += chars[startIndex + 1];
            subStr.add(subs);
            dfs(chars, startIndex + 2, subStr, result);
            subStr.remove(subStr.size() - 1);
        }


    }


    public static void main(String[] args) {
        SolutionLintCode680 solutionLintCode680 = new SolutionLintCode680();
        solutionLintCode680.splitString("123");
    }
}
