package algorithm.lintcode;

import java.util.*;

/**
 * @ClassName: SolutionLintCode57
 * @Author: jicheng
 * @CreateDate: 2019/12/10 9:34 PM
 */
public class SolutionLintCode57 {


    private class Num{
        public int f;
        public int m;
        public int t;

        public Num(int f, int m, int t){
            this.f = f;
            this.m = m;
            this.t = t;
        }

        public Num(List<Integer> list){
            this.f = list.get(0);
            this.m = list.get(1);
            this.t = list.get(2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Num num = (Num) o;
            return f == num.f &&
                    m == num.m &&
                    t == num.t;
        }

        @Override
        public int hashCode() {

            return Objects.hash(f, m, t);
        }

    }
    /**
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here

        if(numbers == null || numbers.length<3){
            return new ArrayList<>();
        }


        Set<Num> visited = new HashSet<>();

        List<List<Integer>> result = new ArrayList<>();
        //Arrays.sort(numbers);
        for(int i = numbers.length-1;i>=2;i--){
            int start = 0;
            int target = 0 - numbers[i];
            Set<Integer> s = new HashSet<>();
            while(start<i){

                int tem = target - numbers[start];
                List<Integer> l = new ArrayList<>();
                l.add(numbers[start]);
                l.add(tem);
                l.add(numbers[i]);
                Collections.sort(l);

                Num nu = new Num(l);
                if(s.contains(tem) && !visited.contains(nu)){
                    result.add(l);
                    visited.add(nu);
                }
                s.add(numbers[start++]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,-1,-4};
        SolutionLintCode57 solutionLintCode57 = new SolutionLintCode57();
        List<List<Integer>> lists = solutionLintCode57.threeSum(arr);
        System.out.println(lists);
    }
}
