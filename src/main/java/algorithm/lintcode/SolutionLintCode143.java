package algorithm.lintcode;

/**
 * 描述
 * 给定一个有n个对象（包括k种不同的颜色，并按照1到k进行编号）的数组，
 * 将对象进行分类使相同颜色的对象相邻，并按照1,2，...k的顺序进行排序。
 *
 * @ClassName: SolutionLintCode143
 * @Author: jicheng
 * @CreateDate: 2019/12/11 3:43 PM
 */
public class SolutionLintCode143 {

    /**
     * @param colors: A list of integer
     * @param k:      An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors==null || colors.length == 0 || colors.length == 1){
            return;
        }
        sort(colors, 0, colors.length - 1, 1, k);
    }

    private void sort(int[] colors, int start, int end, int kf, int kl) {
        if (kf >= kl) {
            return;
        }

        int midK = (kl - kf) / 2 + kf;
        int s = start, e = end;

        while (s <= e) {
            // TODO jicheng 这个第一个while，要使用小于等于，
            // 否则有问题：因为midK这时候使用是一个趋近于前面的值，因为1~k（没有0），
            // 所以我要保证midK在前面的区间里面，要是再后面的区间，那么第二次递归，
            // 就要使用midK~kl，如果当前层级，并没有移动s指针的话，那么，就会造成无限递归了
            while (s <= e && colors[s] <= midK) {
                s++;
            }
            while (s <= e && colors[e] > midK) {
                e--;
            }
            if (s <= e) {
                int temp = colors[s];
                colors[s++] = colors[e];
                colors[e--] = temp;
            }
        }
        sort(colors, start, e, kf, midK);
        sort(colors, s, end, midK+1, kl);
    }

    public static void main(String[] args) {
        int[] arr = {2,1,3,4,4,3,1,2,3,4,1,2,2};
        SolutionLintCode143 solutionLintCode143 = new SolutionLintCode143();
        solutionLintCode143.sortColors2(arr,4);
    }
}
