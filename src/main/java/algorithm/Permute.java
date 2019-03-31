package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jicheng on 2017/4/27.
 */
public class Permute {
    public List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        boolean[] flags = new boolean[nums.length];
        Arrays.fill(flags, false);
        List<Integer> subset = new ArrayList<>();
        solution(subset, flags, nums, result);
        return result;
    }

    private void solution(List<Integer> subset, boolean[] flags, int[] nums, List<List<Integer>> result) {
        if (subset.size() == nums.length) {
            result.add(new ArrayList<Integer>(subset));
        }
        for (int i = 0; i < nums.length; i++) {
            if (flags[i]) {
                continue;
            }
            subset.add(nums[i]);
            flags[i] = true;
            solution(subset, flags, nums, result);
            subset.remove(subset.size() - 1);
            flags[i] = false;
        }
    }

}
