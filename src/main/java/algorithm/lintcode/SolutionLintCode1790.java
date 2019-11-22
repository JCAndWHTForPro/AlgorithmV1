package algorithm.lintcode;

import java.util.LinkedList;

/**
 * 给出一个字符串(以字符数组形式给出)，一个右偏移和一个左偏移，根据给出的偏移量循环移动字符串。
 * (left offest表示字符串向左的偏移量，right offest表示字符串向右的偏移量，左偏移量和右偏移量计算得到总偏移量，
 * 在总偏移量处分成两段字符串并交换位置)。
 * <p>
 * 样例 1:
 * <p>
 * 输入：str ="abcdefg", left = 3, right = 1
 * 输出："cdefgab"
 * 解释：左偏移量为3，右偏移量为1，总的偏移量为向左2，故原字符串向左移动，变为"cdefg" + "ab"。
 * <p>
 * <p>
 * 样例 2:
 * <p>
 * 输入：str="abcdefg", left = 0, right = 0
 * 输出："abcdefg"
 * 解释：左偏移量为0，右偏移量为0，总的偏移量0，故字符串不变。
 * 样例 3:
 * <p>
 * 输入：str = "abcdefg",left = 1, right = 2
 * 输出："gabcdef"
 * 解释：左偏移量为1，右偏移量为2，总的偏移量为向右1，故原字符串向右移动，变为"g" + "abcdef"。
 *
 * @ClassName: SolutionLintCode1790
 * @Author: jicheng
 * @CreateDate: 2019/11/22 2:02 PM
 */
public class SolutionLintCode1790 {


    /**
     * @param str:   An array of char
     * @param left:  a left offset
     * @param right: a right offset
     * @return: return a rotate string
     */
    public String RotateString2(String str, int left, int right) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (left == right) {
            return str;
        }
        int length = str.length();
        int diff = (left - right) % length;
        if (diff < 0) {
            diff = length + diff;
        }


        return str.substring(diff, length) + str.substring(0, diff);
    }


    public static void main(String[] args) {
        System.out.println(new SolutionLintCode1790().RotateString2("abcdefg", 1123, 2234));
    }

}


