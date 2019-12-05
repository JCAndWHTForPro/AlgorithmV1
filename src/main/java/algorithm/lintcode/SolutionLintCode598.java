package algorithm.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 给一个二维网格，每一个格子都有一个值，2 代表墙，1 代表僵尸，
 * 0 代表人类(数字 0, 1, 2)。僵尸每天可以将上下左右最接近的人类感染成僵尸，
 * 但不能穿墙。将所有人类感染为僵尸需要多久，如果不能感染所有人则返回 -1。
 * <p>
 * 例1:
 * <p>
 * 输入:
 * [[0,1,2,0,0],
 * [1,0,0,2,1],
 * [0,1,0,0,0]]
 * 输出:
 * 2
 * 例2:
 * <p>
 * 输入:
 * [[0,0,0],
 * [0,0,0],
 * [0,0,1]]
 * 输出:
 * 4
 * <p>
 * TODO jicheng 这个也可以做，比较简单
 *
 * @ClassName: SolutionLintCode598
 * @Author: jicheng
 * @CreateDate: 2019/12/4 12:44 AM
 */
public class SolutionLintCode598 {

    private static class Point {
        public int x;
        public int y;

        public Point(int a, int b) {
            x = a;
            y = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }
    }

    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        // write your code here
        if (grid == null) {
            return -1;
        }
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        if (grid.length == 1 && grid[0].length == 1) {
            if (grid[0][0] == 0 || grid[0][0] == 2) {
                return -1;
            } else {
                return 0;
            }

        }

        HashSet<Point> humanVisited = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Point point = new Point(i, j);
                if (grid[i][j] == 1) {
                    queue.offer(point);
                } else if (grid[i][j] == 0) {
                    humanVisited.add(point);
                }
            }
        }

        int days = 0;
        int deltax[] = {1, -1, 0, 0};
        int deltay[] = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                Point poll = queue.poll();
                for (int delta = 0; delta < deltax.length; delta++) {
                    int x = poll.x + deltax[delta];
                    int y = poll.y + deltay[delta];
                    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                        continue;
                    }
                    if (grid[x][y] == 2 || grid[x][y] == 1) {
                        continue;
                    }
                    if (grid[x][y] == 0) {
                        flag = true;
                        Point newp = new Point(x, y);
                        queue.offer(newp);
                        humanVisited.remove(newp);
                        grid[x][y] = 1;
                    }

                }

            }
            if(flag){
                days++;
            }

        }
        if(humanVisited.isEmpty()){
            return days;
        }

        return -1;
    }

    public static void main(String[] args) {
        int grid[][] = {
                {0,1,2,0,0},
                {1,0,0,2,1},
                {0,1,0,0,0}
        };
        SolutionLintCode598 solutionLintCode598 = new SolutionLintCode598();
        int zombie = solutionLintCode598.zombie(grid);
        System.out.println(zombie);
    }
}
