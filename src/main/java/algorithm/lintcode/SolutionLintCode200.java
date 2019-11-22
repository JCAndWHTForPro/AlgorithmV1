package algorithm.lintcode;

/**
 * 给出一个字符串（假设长度最长为1000），求出它的最长回文子串，
 * 你可以假定只有一个满足条件的最长回文串。
 *
 * @ClassName: SolutionLintCode200
 * @Author: jicheng
 * @CreateDate: 2019/11/22 2:02 PM
 */
public class SolutionLintCode200 {


    /**
     * 注解：通过添加#来解决几种对称性问题
     *
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.equals("")) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");

        char[] charArray = sb.toString().toCharArray();
        int index = 0;
        int maxLenth = 0;

        int charArrLen = charArray.length;
        for (int i = 0; i < charArrLen; i++) {
            int tempLen = 0;
            for (int j = 1; j <= i && j < charArrLen - i; j++) {
                if (charArray[i - j] == charArray[i + j]) {
                    tempLen++;
                }else{
                    break;
                }
            }
            if (tempLen > maxLenth) {
                index = i;
                maxLenth = tempLen;
            }
        }

        if (maxLenth != 0) {
            int begin = index - maxLenth;
            int end = index + maxLenth + 1;
            return sb.substring(begin, end).replaceAll("#", "");
        }
        return "";
    }

    public static void main(String[] args) {
        SolutionLintCode200 solutionLintCode200 = new SolutionLintCode200();
        String s = solutionLintCode200.longestPalindrome("abcdzdcab");
        System.out.println(s);
    }


}
