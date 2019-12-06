package algorithm.lintcode;

/**
 * @ClassName: SolutionLintCode62
 * @Author: jicheng
 * @CreateDate: 2019/12/7 2:04 AM
 */
public class SolutionLintCode62 {

    public int search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        if (A.length == 1 && A[0] == target) {
            return 0;
        }
        if (A.length == 1 && A[0] != target) {
            return -1;
        }

        int cutNum = A[A.length - 1];
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (A[mid] < cutNum) {
                end = mid;
            } else {
                start = mid;
            }
        }

        int smallestNum = A[start] > A[end] ? A[end] : A[start];
        int smallestIndex = A[start] > A[end] ? end : start;
        //int result = -1;
        int s, e;

        if (target >= smallestNum && target <= cutNum) {
            s = smallestIndex;
            e = A.length - 1;
        } else {
            s = 0;
            e = smallestIndex > 0 ? smallestIndex - 1 : 0;
        }

        while (s + 1 < e) {
            int mid = (e - s) / 2 + s;
            if (A[mid] == target) {
                return mid;
            }
            if (A[mid] < target) {
                s = mid;
            } else {
                e = mid;
            }
        }
        if (A[s] == target) {
            return s;
        }
        if (A[e] == target) {
            return e;
        }

        return -1;
    }

    public static void main(String[] args) {
        SolutionLintCode62 solutionLintCode62 = new SolutionLintCode62();
        int[] A = {1, 2, 3};
        System.out.println(solutionLintCode62.search(A, 1));
    }
}
