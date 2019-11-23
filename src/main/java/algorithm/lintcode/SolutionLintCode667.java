package algorithm.lintcode;

/**
 * 给一字符串 s, 找出在 s 中的最长回文子序列的长度. 你可以假设 s 的最大长度为 1000.
 * <p>
 * 样例1
 * <p>
 * 输入： "bbbab"
 * 输出： 4
 * 解释：
 * 一个可能的最长回文序列为 "bbbb"
 * 样例2
 * <p>
 * 输入： "bbbbb"
 * 输出： 5
 *
 * @ClassName: SolutionLintCode667
 * @Author: jicheng
 * @CreateDate: 2019/11/22 2:02 PM
 */
public class SolutionLintCode667 {


    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null) {
            return 0;
        }

        int length = s.length();
        int[][] resultMap = new int[length][length];
        for(int i =0;i<length;i++){
            for(int j = 0;j<length;j++){
                if(i == j){
                    resultMap[i][j] = 1;
                }
            }
        }
        return subStr(s, 0, length - 1,resultMap);

    }

    private int subStr(String s, int begin, int end, int[][] resultMap) {
        if (end < begin) {
            return 0;
        }
        if(resultMap[begin][end]!=0){
            return resultMap[begin][end];
        }
        if (s.charAt(begin) == s.charAt(end)) {
            int i = subStr(s, begin + 1, end - 1, resultMap) + 2;
            resultMap[begin][end] = i;
            return i;
        }
        int max = Math.max(subStr(s, begin + 1, end, resultMap), subStr(s, begin, end - 1, resultMap));
        resultMap[begin][end] = max;
        return max;
    }



    public static void main(String[] args) {
        System.out.println(new SolutionLintCode667().longestPalindromeSubseq("asdasdajjdkajwiejladjkahsdjhawiueauwhdjashdjancnkjsahduiawudhajsnhsjahjdhawuahdjshjnzanjcnhjdashuawhdjaksndjkahduwhwauhdai"));
    }

}


