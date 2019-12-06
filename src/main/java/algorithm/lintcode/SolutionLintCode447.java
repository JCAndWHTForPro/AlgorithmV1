package algorithm.lintcode;

/**
 * @ClassName: SolutionLintCode447
 * @Author: jicheng
 * @CreateDate: 2019/12/6 11:38 PM
 */
public class SolutionLintCode447 {

    public static class ArrayReader {

        private int[] arr = {3,4,5,8,8,8,8,10,13,14};

        public int get(int index) {
            // return the number on given index,
            // return 2147483647 if the index is invalid.
            if(index>=0 && index<=arr.length){
                return arr[index];
            }
            return 2147483647;
        }
    }

    /*
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return: An integer which is the first index of target.
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
        // write your code here
        if(reader == null){
            return -1;
        }
        if(reader.get(0) == target){
            return 0;
        }
        int k = 1;
        while(reader.get(k)<2147483647){
            k *= 2;
        }

        int start = 0,end = k;
        while(start+1<end){
            int mid = (end-start)/2 + start;
            if(reader.get(mid) == target){
                end = mid;
                continue;
            }
            if(reader.get(mid)>target){
                end = mid;
            }else{
                start = mid;
            }
        }

        if(reader.get(start) == target){
            return start;
        }
        if(reader.get(end) == target){
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        SolutionLintCode447 solutionLintCode447 = new SolutionLintCode447();
        int i = solutionLintCode447.searchBigSortedArray(new ArrayReader(), 8);
        System.out.println(i);
    }

}
