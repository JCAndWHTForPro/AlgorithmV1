package algorithm.lintcode;

/**
 * 在数组中找到第 k 大的元素。
 *
 * @ClassName: SolutionLintCode5
 * @Author: jicheng
 * @CreateDate: 2019/12/10 9:48 PM
 */
public class SolutionLintCode5 {

    /**
     * @param n:    An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
        if (nums.length == 1) {
            return nums[0];
        }

        return kthLargestElement(nums, 0, nums.length - 1, n);
    }

    private int kthLargestElement(int[] nums, int start, int end, int n) {
        if (start == end) {
            return nums[start];
        }
        int s = start, e = end;
        int mid = (e - s) / 2 + s;
        int num = nums[mid];
        while (s <= e) {
            // 这两个while里面的大于小于很有说法，注意：
            // 1、要s和e是小于等于的关系；
            // 2、注意s和e两次分别是岔开的，不能同时等于关系，否则两指针很可能就不闭合了
            // 3、第一个while里面还不能有等号，否则，会越界
            while (s <= e && nums[s] > num) {
                s++;
            }
            // s 一直指向nums[s]<=num的第一个
            while (s <= e && nums[e] <= num) {
                e--;
            }
            // e 一直指向nums[e]>num的那个
            if (s <= e) {
                int temp = nums[s];
                nums[s++] = nums[e];
                nums[e--] = temp;
            }
        }

        // 这个if很巧妙，这种情况说明，第一个字符，就是最大（最小）字符的第一个（分割点）；
        // 不加的话就有问题，因为无法吧第k大（小）这个层级中要剔除的给干掉，然后就无线递归了
        if(s == start){
            s++;
        }
        if (s - start >= n) {
            return kthLargestElement(nums, start, s - 1, n);
        } else {
            return kthLargestElement(nums, s, end, n - (s - start));
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,8,9,10,7};
        SolutionLintCode5 solutionLintCode5 = new SolutionLintCode5();
        int i = solutionLintCode5.kthLargestElement(10, arr);
        System.out.println(i);
    }
}
