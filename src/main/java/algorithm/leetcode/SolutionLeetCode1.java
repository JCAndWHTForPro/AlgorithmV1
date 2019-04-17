package algorithm.leetcode;

import java.util.HashMap;

/**
 * @ClassName: SolutionLeetCode1
 * @Author: jicheng
 * @CreateDate: 2019/4/17 上午12:11
 */
public class SolutionLeetCode1 {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> numberMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int firstNumber = nums[i];
            int secondNumber = target - firstNumber;
            Integer secondNumberIndex = numberMap.get(secondNumber);
            if (secondNumberIndex != null) {
                result[0] = i;
                result[1] = secondNumberIndex.intValue();
                break;
            } else {
                numberMap.put(firstNumber, i);
            }
        }


        return result;
    }

    public static void main(String[] args) {
        int[] twoSum = new SolutionLeetCode1().twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(twoSum[0]+","+twoSum[1]);
    }

}
