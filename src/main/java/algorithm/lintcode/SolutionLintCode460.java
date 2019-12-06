package algorithm.lintcode;

/**
 * @ClassName: SolutionLintCode460
 * @Author: jicheng
 * @CreateDate: 2019/12/7 1:03 AM
 */
public class SolutionLintCode460 {

    /**
     * @param A:      an integer array
     * @param target: An integer
     * @param k:      An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
        if (A == null || A.length == 0) {
            return new int[0];
        }
        if (A.length == 1) {

            return A;
        }

        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (A[mid] == target) {
                end = mid;
                continue;
            }
            if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        int result[] = new int[k];
        int i = 0;
        while (start >= 0 && i < k && A[start] == target) {
            result[i++] = A[start--];
        }
        while (end < A.length && i < k && A[end] == target) {
            result[i++] = A[end++];
        }
        while (start >= 0 && end < A.length && i < k) {
            int startArg = Math.abs(A[start] - target);
            int endArg = Math.abs(A[end] - target);
            if (startArg < endArg) {
                result[i++] = A[start--];
            } else if (startArg > endArg) {
                result[i++] = A[end++];
            } else {
                result[i++] = A[start--];
                if(i<k) {
                    result[i++] = A[end++];
                }
            }
        }
        while (i < k && start >= 0) {
            result[i++] = A[start--];
        }
        while (i < k && end < A.length) {
            result[i++] = A[end++];
        }
        return result;
    }

    public static void main(String[] args) {
        SolutionLintCode460 solutionLintCode460 = new SolutionLintCode460();
        int A[] = {1,10,15,25,35,45,50,59};
        int[] ints = solutionLintCode460.kClosestNumbers(A, 30, 7);
        System.out.println(ints);
    }
}
