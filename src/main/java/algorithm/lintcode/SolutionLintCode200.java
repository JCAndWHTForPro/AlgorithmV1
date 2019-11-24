package algorithm.lintcode;

/**
 * 给出一个字符串（假设长度最长为1000），求出它的最长回文子串，
 * 你可以假定只有一个满足条件的最长回文串。
 *
 * TODO jicheng 1、暴力双循环+内部判断回文方法（o(n^3)）
 * TODO jicheng 2、从长到短进行判断，时间复杂度同上
 * TODO jicheng 3、从短到长进行判断，会好点
 * TODO jicheng 4、动态规划模式进行求解（记住已经是回文的坐标，下次直接使用，这是动规）
 *
 * 当下自己写的这个加#的，类似于第三种
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
