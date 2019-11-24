package algorithm.lintcode;

/**
 * 实现时间复杂度为 O(n + m)的方法 strStr。
 * strStr 返回目标字符串在源字符串中第一次出现的第一个字符的位置. 目标字串的长度为 m , 源字串的长度为 n .
 * 如果目标字串不在源字串中则返回 -1。
 * <p>
 * 样例 1:
 * <p>
 * 输入：source = "abcdef"， target = "bcd"
 * 输出：1
 * 解释：
 * 字符串第一次出现的位置为1。
 * 样例 2:
 * <p>
 * 输入：source = "abcde"， target = "e"
 * 输出：4
 * 解释：
 * 字符串第一次出现的位置为4。
 * <p>
 * <p>
 * jicheng 让我们一起用Rabin Karp 算法
 *
 * @ClassName: SolutionLintCode594
 * @Author: jicheng
 * @CreateDate: 2019/11/24 11:13 PM
 */
public class SolutionLintCode594 {


    private static final int BASE_MODE = 1000000;

    /*
     * @param source: A source string
     * @param target: A target string
     * @return: An integer as index
     */
    public int strStr2(String source, String target) {

        if(source == null || target == null){
            return -1;
        }
        if(source.equals(target)){
            return 0;
        }
        if("".equals(target)){
            return 0;
        }

        int mLength = target.length();

        int pow = 1;
        int targetHash = 0;


        for (int i = 0; i < mLength; i++) {
            //TODO jicheng 这里注意点：如果target太长，这里pow很大，导致后面直接使用source的一个字符去乘会导致溢出
            pow = (pow * 31)% BASE_MODE;
            targetHash = (targetHash * 31 + target.charAt(i)) % BASE_MODE;
        }

        int sourceHash = 0;
        for (int i = 0; i < source.length(); i++) {
            sourceHash = (sourceHash * 31 + source.charAt(i)) % BASE_MODE;

            if (i >= mLength - 1) {
                if (i >= mLength) {
                    // TODO jicheng source.charAt(i - mLength) * pow  这句有溢出风险
                    sourceHash = sourceHash - (source.charAt(i - mLength) * pow) % BASE_MODE;
                    if (sourceHash < 0) {
                        sourceHash += BASE_MODE;
                    }
                }
                // 这个东西是原始串的平移起始字符
                int distance = i - mLength + 1;
                if (sourceHash == targetHash && source.substring(distance, i + 1).equals(target)) {
                    return distance;
                }
            }

        }

        return -1;
    }


    public static void main(String[] args) {
        SolutionLintCode594 solutionLintCode594 = new SolutionLintCode594();
        System.out.println(solutionLintCode594.strStr2("tartarget", "target"));
    }

}
