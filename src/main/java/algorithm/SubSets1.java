package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jicheng on 2017/4/23.
 */
public class SubSets1 {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(nums);
        boolean[] flags = new boolean[nums.length];
        ArrayList<boolean[]> flagsResult = new ArrayList<>();
        this.assambleList(flags, flagsResult, 0);

        calculateResult(nums, result, flagsResult);
        return result;
    }

    private void calculateResult(int[] nums, ArrayList<ArrayList<Integer>> result, ArrayList<boolean[]> flagsResult) {
        for (boolean[] it : flagsResult) {
            ArrayList<Integer> subResult = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (it[i]) {
                    subResult.add(nums[i]);
                }
            }
            result.add(subResult);
        }
    }

    private void assambleList(boolean[] flags, ArrayList<boolean[]> result, int index) {
        if (index == flags.length) {
            boolean[] listFlags = new boolean[flags.length];
            System.arraycopy(flags, 0, listFlags, 0, flags.length);
            result.add(listFlags);
        } else {
            flags[index] = true;
            assambleList(flags, result, index + 1);
            flags[index] = false;
            assambleList(flags, result, index + 1);

        }
    }

}
