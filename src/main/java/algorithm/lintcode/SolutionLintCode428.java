package algorithm.lintcode;

/**
 * @ClassName: Solution428
 * @Author: jicheng
 * @CreateDate: 2019/12/6 1:33 PM
 */
public class SolutionLintCode428 {

    public double myPow(double x, int n) {
        // write your code here

        double base = x;
        double result = 1.0;

        boolean nagetive = false;
        long ln = (long) n;
        if (n < 0) {
            nagetive = true;
            ln = -ln;
        }

        while (ln != 0l) {
            if (ln % 2L == 1L) {
                result = result * base;
            }
            base = base * base;
            ln = ln / 2L;
        }

        return nagetive ? 1 / result : result;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
//        System.out.println(new Solution428().myPow(2.00000, -2147483648));
        System.out.println(123%0);
    }
}
