package algorithm.lintcode;

/**
 * lintCode 第 41 题
 *
 * @ClassName: Solution41
 * @Author: jicheng
 * @CreateDate: 2019/4/16 下午9:19
 */
public class SolutionLintCode41 {

    public static int solution(int[] nums) {
        int result = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > result) {
                result = sum;
            }
            if (sum <= 0) {
                sum = 0;

            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,-1,-1,-3,-2};
        System.out.println(solution(nums));
    }
}
