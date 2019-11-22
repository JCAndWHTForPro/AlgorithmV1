package algorithm.lintcode;

/**
 * 对于一个给定的 source 字符串和一个 target 字符串，
 * 你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。
 * 如果不存在，则返回 -1。
 *
 * @ClassName: SolutionLintCode13
 * @Author: jicheng
 * @CreateDate: 2019/11/22 2:02 PM
 */
public class SolutionLintCode415 {



    public int solution(String srcStr, String targetStr) {
        int modNum = 100000;
        if (srcStr == null || targetStr == null) {
            return -1;
        }
        if (targetStr.length() > srcStr.length()) {
            return -1;
        }
        if ("".equals(targetStr) || srcStr.equals(targetStr)) {
            return 0;
        }
        int targetLength = targetStr.length();
        int power = 1;
        int targetHash = 0;
        for (int i = 0; i < targetLength; i++) {
            power = (power * 31) % modNum;
            targetHash = (31 * targetHash + targetStr.charAt(i)) % modNum;
        }

        int srcHash = 0;
        for (int i = 0; i < srcStr.length(); i++) {
            srcHash = (31 * srcHash + srcStr.charAt(i)) % modNum;
            if (i >= targetLength - 1) {
                if (i > targetLength - 1) {
                    // 这里已经超过目标子串的长度的时候，要把哈希值减去
                    srcHash = srcHash - (power * srcStr.charAt(i - targetLength)) % modNum;
                    if (srcHash < 0) {
                        srcHash += modNum;
                    }
                }
                if (srcHash == targetHash && targetStr.equals(srcStr.substring(i - targetLength + 1, i + 1))) {
                    return i - targetLength + 1;
                }
            }
        }
        return -1;
    }


}
