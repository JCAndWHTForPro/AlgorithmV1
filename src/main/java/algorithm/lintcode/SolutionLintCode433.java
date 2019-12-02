package algorithm.lintcode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿个数
 * <p>
 * 描述
 * 给一个 01 矩阵，求不同的岛屿的个数。
 * <p>
 * 0 代表海，1 代表岛，如果两个 1 相邻，那么这两个 1 属于同一个岛。我们只考虑上下左右为相邻。
 * <p>
 * <p>
 * 输入：
 * [
 * [1,1,0,0,0],
 * [0,1,0,0,1],
 * [0,0,0,1,1],
 * [0,0,0,0,0],
 * [0,0,0,0,1]
 * ]
 * 输出：
 * 3
 *
 * @ClassName: SolutionLintCode433
 * @Author: jicheng
 * @CreateDate: 2019/12/2 10:24 PM
 */
public class SolutionLintCode433 {


    public static class Cordinate {
        public int x;
        public int y;

        public Cordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int result = 0;
        Queue<Cordinate> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    queue.offer(new Cordinate(i, j));
                }
            }
        }

        while (true) {
            if (queue.isEmpty()) {
                break;
            }
            Cordinate poll = queue.poll();
            if (grid[poll.x][poll.y]) {
                result++;
            } else {
                continue;
            }
            grid = handleGrid(grid, poll);
        }


        return result;
    }

    private boolean[][] handleGrid(boolean[][] grid, Cordinate poll) {
        grid[poll.x][poll.y] = false;
        Queue<Cordinate> queue = new LinkedList<>();
        queue.offer(poll);
        int[] deltaX = {1, -1, 0, 0};
        int[] deltaY = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Cordinate cordinate = queue.poll();
            for (int i = 0; i < deltaX.length; i++) {
                int x = cordinate.x + deltaX[i];
                int y = cordinate.y + deltaY[i];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
                    continue;
                }
                if (grid[x][y]) {
                    grid[x][y] = false;
                    queue.offer(new Cordinate(x, y));

                }
            }
        }

        return grid;
    }

    public static void main(String[] args) {
        boolean grid[][] = {
                {true, true, false, false, false},
                {false, true, false, false, true},
                {false, false, false, true, true},
                {false, false, false, false, false},
                {false, false, false, false, true}
        };
        /*boolean grid[][] = {
                {true,true}
        };*/
        SolutionLintCode433 solutionLintCode433 = new SolutionLintCode433();
        int i = solutionLintCode433.numIslands(grid);
        System.out.println(i);
    }
}
