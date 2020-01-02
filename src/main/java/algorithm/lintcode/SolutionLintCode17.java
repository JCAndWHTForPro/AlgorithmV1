package algorithm.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 17. 子集
 * 给定一个含不同整数的集合，返回其所有的子集。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：[0]
 * 输出：
 * [
 * [],
 * [0]
 * ]
 * 样例 2：
 * <p>
 * 输入：[1,2,3]
 * 输出：
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 *
 * @ClassName: SolutionLintCode17
 * @Author: jicheng
 * @CreateDate: 2019/12/29 6:20 PM
 */
public class SolutionLintCode17 {


    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // write your code here
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);


        subsets2(nums, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     *  @param i 这里的i代表第i个点
     */
    private void subsets(int[] nums, int i, List<Integer> subList, List<List<Integer>> result) {
        if (i == nums.length) {
            result.add(new ArrayList<>(subList));
            return;
        }
//            List<Integer> subList = new ArrayList<>();
        subList.add(nums[i]);
        subsets(nums, i + 1, subList, result);

        subList.remove(subList.size() - 1);
        subsets(nums, i + 1, subList, result);


    }

    private void subsets2(int[] nums, int startIndex, List<Integer> subList, List<List<Integer>> results) {
        results.add(new ArrayList<>(subList));
        for (int i = startIndex; i < nums.length; i++) {
            subList.add(nums[i]);
            subsets2(nums, i + 1, subList, results);
            subList.remove(subList.size() - 1);
        }
    }

    public static void main(String[] args) {
        SolutionLintCode17 solutionLintCode17 = new SolutionLintCode17();
        int[] arr = {4, 5, 6};
        List<List<Integer>> subsets = solutionLintCode17.subsets(arr);
        System.out.println(subsets);
    }
}
