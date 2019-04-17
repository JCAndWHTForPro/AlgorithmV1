package algorithm.base;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jicheng on 2017/4/24.
 * 使用算法课上老师使用的深度优先遍历的方式求解
 */
public class SubSets2 {
    public ArrayList<ArrayList<Integer>> solution(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(nums);
        ArrayList<Integer> subset = new ArrayList<>();
        solution(nums, 0, subset, result);
        return result;
    }

    private void solution(int[] nums,
                          int begin,
                          ArrayList<Integer> subset,
                          ArrayList<ArrayList<Integer>> result) {
        result.add(new ArrayList<>(subset));
        for (int i = begin; i < nums.length; i++) {
            if (i - 1 >= 0 && nums[i - 1] == nums[i] && i > begin) {//放置重复元素造成的重复子集的存在加的判断
                continue;
            }
            subset.add(nums[i]);
            solution(nums, i + 1, subset, result);
            subset.remove(subset.size() - 1);
        }
    }
}
