package algorithm.lintcode;

/**
 * @ClassName: SolutionLintCode140
 * @Author: jicheng
 * @CreateDate: 2019/12/6 2:25 PM
 */
public class SolutionLintCode140 {

    public int fastPower(int a, int b, int n) {
        // write your code here
        // write your code here

        long base = (long) a;
        long result = 1L;

        long ln = (long) n;


        while (ln != 0l) {
            if (ln % 2L == 1L) {
                result = result * base % (long) b;
            }
            base = base * base % (long)b;
            ln = ln / 2L;
        }
        //int r = nagetive ? 1 / result : result
        return (int)result;
    }

    public static void main(String[] args) {
        SolutionLintCode140 solutionLintCode140 = new SolutionLintCode140();
        int i = solutionLintCode140.fastPower(11, 123898, 12345);
        System.out.println(i);
    }
}
