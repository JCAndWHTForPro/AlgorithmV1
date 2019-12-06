package algorithm.lintcode;

/**
 * @ClassName: SolutionLintCode585
 * @Author: jicheng
 * @CreateDate: 2019/12/7 12:04 AM
 */
public class SolutionLintCode585 {

    public int mountainSequence(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0){
            return -1;
        }

        if(nums.length == 1){
            return nums[0];
        }

        int start = 0,end = nums.length-1;

        while(start+1<end){
            int mid = (end - start)/2 + start;

            if(nums[mid]>nums[mid-1]){
                start = mid;
            }
            if(nums[mid]>nums[mid+1]){
                end = mid;
            }
        }

        if(nums[start]>nums[end]){
            return nums[start];
        }else{
            return nums[end];
        }

    }

    public static void main(String[] args) {
        SolutionLintCode585 solutionLintCode585 = new SolutionLintCode585();
        int[] arr = {10,9,8,7};
        int i = solutionLintCode585.mountainSequence(arr);
        System.out.println(i);
    }
}
