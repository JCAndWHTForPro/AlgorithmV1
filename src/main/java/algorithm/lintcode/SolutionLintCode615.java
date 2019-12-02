package algorithm.lintcode;


import java.util.*;

/**
 * 基础的课程表问题：
 * 现在你总共有 n 门课需要选，记为 0 到 n - 1.
 * 一些课程在修之前需要先修另外的一些课程，比如要学习课程 0 你需要先学习课程 1 ，表示为[0,1]
 * 给定n门课以及他们的先决条件，判断是否可能完成所有课程？
 * <p>
 * 例1:
 * <p>
 * 输入: n = 2, prerequisites = [[1,0]]
 * 输出: true
 * 例2:
 * <p>
 * 输入: n = 2, prerequisites = [[1,0],[0,1]]
 * 输出: false
 *
 * @ClassName: SolutionLintCode615
 * @Author: jicheng
 * @CreateDate: 2019/12/3 12:27 AM
 */
public class SolutionLintCode615 {


    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here


        if (prerequisites == null ) {
            return false;
        }

        if(prerequisites.length==0|| prerequisites[0].length == 0){
            return true;
        }


        int[] inDegress = new int[numCourses];
        Map<Integer,List<Integer>> mapping = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            for (int j = 0; j < prerequisites[i].length - 1; j++) {
                inDegress[prerequisites[i][j]] += 1;
//                preNode[prerequisites[i][j + 1]] = prerequisites[i][j];
                int key = prerequisites[i][j + 1];
                List<Integer> integers = mapping.get(key);
                if(integers == null){
                    integers = new ArrayList<>();
                    mapping.put(key,integers);
                }
                integers.add(prerequisites[i][j]);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegress.length; i++) {
            if (inDegress[i] == 0) {
                queue.offer(Integer.valueOf(i));
            }
        }

        int len = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                len++;
                List<Integer> integers = mapping.get(poll);
                if(integers == null){
                    continue;
                }
                for(Integer integer : integers){
                    int intValue = integer.intValue();
                    inDegress[intValue] -= 1;
                    if(inDegress[intValue] <= 0){
                        queue.offer(Integer.valueOf(intValue));
                    }
                }
            }
        }

        if(len == numCourses){
            return true;
        }


        return false;

    }

    public static void main(String[] args) {
        int[][] pre = {{1,0},{0,1}};
        SolutionLintCode615 solutionLintCode615 = new SolutionLintCode615();
        boolean b = solutionLintCode615.canFinish(2, pre);
        System.out.println(b);

    }
}
