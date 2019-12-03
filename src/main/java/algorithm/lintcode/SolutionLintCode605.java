package algorithm.lintcode;

import java.util.*;

/**
 * 判断是否序列 org 能唯一地由 seqs重构得出. org是一个由从1到n的正整数排列而成的序列，1 ≤ n ≤ 10^4。
 * 重构表示组合成seqs的一个最短的父序列 (意思是，一个最短的序列使得所有 seqs里的序列都是它的子序列).
 * 判断是否有且仅有一个能从 seqs重构出来的序列，并且这个序列是org。
 * <p>
 * 例1:
 * <p>
 * 输入:org = [1,2,3], seqs = [[1,2],[1,3]]
 * 输出: false
 * 解释:
 * [1,2,3] 并不是唯一可以被重构出的序列，还可以重构出 [1,3,2]
 * 例2:
 * <p>
 * 输入: org = [1,2,3], seqs = [[1,2]]
 * 输出: false
 * 解释:
 * 能重构出的序列只有 [1,2].
 * 例3:
 * <p>
 * 输入: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
 * 输出: true
 * 解释:
 * 序列 [1,2], [1,3], 和 [2,3] 可以唯一重构出 [1,2,3].
 * 例4:
 * <p>
 * 输入:org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
 * 输出:true
 * <p>
 * TODO jicheng 分析：
 * 1、这个就是一个典型的拓扑排序！只不过要对有多种情况的拓扑排序做一个识别，也简单:
 * 每次往queue里面offer的时候，如果每一次循环offer不止一个，那肯定不是有唯一一种排序序列的！
 * 2、[1,2,3]类似于这种，就是1是2的前置，2是3的前置
 *
 * @ClassName: SolutionLintCode605
 * @Author: jicheng
 * @CreateDate: 2019/12/3 11:04 AM
 */
public class SolutionLintCode605 {

    /**
     * @param org:  a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {

        if (org == null || seqs == null) {
            return false;
        }
        if (org.length == 0 && (seqs.length == 0 || seqs[0].length == 0)) {
            return true;
        }
        if (org.length != 0 && (seqs.length == 0 || seqs[0].length == 0)) {
            return false;
        }

//        int indegress[] = new int[10000];

        Map<Integer, Integer> indegress = new HashMap<>();

        for (int i = 0; i < org.length; i++) {
            indegress.put(org[i], 0);
        }

        Map<Integer, List<Integer>> mapping = new HashMap<>();

        for (int i = 0; i < seqs.length; i++) {
            if(!indegress.containsKey(seqs[i][0])){
                return false;
            }
            for (int j = 1; j < seqs[i].length; j++) {
                int current = seqs[i][j];
                Integer indeNum = indegress.get(current);
                if (indeNum == null) {
                    return false;
                }
                indegress.put(current, indeNum + 1);
                int parent = seqs[i][j - 1];
                List<Integer> ns = mapping.get(parent);
                if (ns == null) {
                    ns = new ArrayList<>();
                    mapping.put(parent, ns);
                }
                ns.add(current);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegress.entrySet()) {
            if (entry.getValue().equals(0)) {
                queue.offer(entry.getKey());
            }
        }
        if (queue.size() > 1) {
            return false;
        }
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result.add(poll);
            List<Integer> integers = mapping.get(poll);
            if(integers == null) continue;
            for (Integer num : integers) {
                Integer degress = indegress.get(num) - 1;
                if (degress <= 0) {
                    queue.offer(num);
                }
                indegress.put(num, degress);
            }
            if (queue.size() > 1) {
                return false;
            }
        }

        if (result.size() != org.length) {
            return false;
        }
        for (int i = 0; i < org.length; i++) {
            if (!Integer.valueOf(org[i]).equals(result.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SolutionLintCode605 solutionLintCode605 = new SolutionLintCode605();
        int org[] = {1};
        int seqs[][] = {{1}};
        boolean b = solutionLintCode605.sequenceReconstruction(org, seqs);
        System.out.println(b);
    }
}
