package algorithm.lintcode;

/**
 * 给出一个包含大小写字母的字符串。求出由这些字母构成的最长的回文串的长度是多少。
 * 数据是大小写敏感的，也就是说，"Aa" 并不会被认为是一个回文串。
 *
 * @ClassName: SolutionLintCode627
 * @Author: jicheng
 * @CreateDate: 2019/11/22 2:02 PM
 */
public class SolutionLintCode627 {


    /**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }

        int[] charArr = new int[256];

        int length = s.length();
        for (int i = 0; i < length; i++) {
            int c = s.charAt(i);
            charArr[c]++;
        }
        int maxLen = length;
        boolean flag = false;
        for (int i = 0; i < length; i++) {
            int c = s.charAt(i);
            boolean isOdd = charArr[c] % 2 > 0;
            if (isOdd) {
                charArr[c]--;
                if (!flag) {
                    flag = true;
                } else {
                    maxLen--;
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new SolutionLintCode627().longestPalindrome("aaa"));
    }


}
