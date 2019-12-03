package algorithm.lintcode;


import java.util.*;

/**
 *
 * @ClassName: SolutionLintCode616
 * @Author: jicheng
 * @CreateDate: 2019/12/3 12:27 AM
 */
public class SolutionLintCode616 {


    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public int[] findOrder(int numCourses, int[][] prerequisites){
        // write your code here


        if (prerequisites == null ) {
            return new int[0];
        }

        int[] result = new int[numCourses];


        int[] inDegress = new int[numCourses];
        Map<Integer,List<Integer>> mapping = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            for (int j = 0; j < prerequisites[i].length - 1; j++) {
                inDegress[prerequisites[i][j]] += 1;
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
                result[len] = poll.intValue();
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

        if(len != numCourses){
            return new int[0];
        }


        return result;

    }

    public static void main(String[] args) {
        int[][] pre = {{1,0},{2,0},{3,1},{3,2}};
        SolutionLintCode616 solutionLintCode615 = new SolutionLintCode616();
        int[] order = solutionLintCode615.findOrder(4, pre);
        System.out.println(Arrays.asList(order));

    }
}
