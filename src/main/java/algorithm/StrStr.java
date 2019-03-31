package algorithm;

/**
 * Created by jicheng on 2017/4/24.
 * 使用Robin-Karp算法
 */
public class StrStr {
    private int modNum = 1000000;

    public int solution(String srcStr, String targetStr) {
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
            power = (power * 31) % this.modNum;
            targetHash = (31 * targetHash + targetStr.charAt(i)) % this.modNum;
        }

        int srcHash = 0;
        for (int i = 0; i < srcStr.length(); i++) {
            srcHash = (31 * srcHash + srcStr.charAt(i)) % this.modNum;
            if (i >= targetLength - 1) {
                if (i > targetLength - 1) {
                    srcHash = srcHash - (power * srcStr.charAt(i - targetLength)) % this.modNum;
                    if (srcHash < 0) {
                        srcHash += this.modNum;
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
