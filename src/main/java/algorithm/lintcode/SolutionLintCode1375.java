package algorithm.lintcode;

/**
 * TODO jicheng 没弄明白~妈的
 * @ClassName: SolutionLintCode1375
 * @Author: jicheng
 * @CreateDate: 2019/12/11 8:08 PM
 */
public class SolutionLintCode1375 {

    /**
     * @param s: a string
     * @param k: an integer
     * @return: the number of substrings there are that contain at least k distinct characters
     */
    public long kDistinctCharacters(String s, int k) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return 0L;
        }
        if (s.length() == 1 && k == 1) {
            return 1L;
        }
        if (s.length() < k) {
            return 0L;
        }
        long reuslt = 0L;

        for (int large = k; large <= s.length(); large++) {
            int charArr[] = new int[256];
            int count = 0;
            for (int i = 0; i + large <= s.length(); i++) {
                if (i > 0) {
                    int c = (--charArr[s.charAt(i - 1)]);
                    if (c == 0) {
                        count--;
                    }
                }

                int start = i;
                int end = i + large - 1;
                while (i == 0 && start <= end) {
                    if (charArr[s.charAt(start)] == 0) {
                        count++;
                    }
                    charArr[s.charAt(start)]++;
                    if (charArr[s.charAt(end)] == 0) {
                        count++;
                    }
                    charArr[s.charAt(end)]++;

                    start++;
                    end--;
                }
                if (i != 0) {
                    if(charArr[s.charAt(end)]==0){
                        count++;
                    }
                    charArr[s.charAt(end)]++;
                }
                if (count >= k) {
                    reuslt++;
                }
            }
        }

        return reuslt;
    }

    public static void main(String[] args) {
        SolutionLintCode1375 solutionLintCode1375 = new SolutionLintCode1375();
        long abcabcabcabc = solutionLintCode1375.kDistinctCharacters("kjjnebwrkdauuhiyjfqigvnfjokcvqudlurmnyipizzzfsbpgislkhsluurygkmkxzrufzkjrwkixxjbzysjzhepjrszqnjemrgx", 10);
        System.out.println(abcabcabcabc);
    }
}
