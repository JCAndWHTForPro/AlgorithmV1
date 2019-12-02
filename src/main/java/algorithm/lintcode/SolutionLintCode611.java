package algorithm.lintcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: SolutionLintCode611
 * @Author: jicheng
 * @CreateDate: 2019/12/2 11:16 PM
 */
public class SolutionLintCode611 {

    /**
     * @param grid:        a chessboard included 0 (false) and 1 (true)
     * @param source:      a point
     * @param destination: a point
     * @return: the shortest path
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        if (source == null || destination == null) {
            return -1;
        }
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        if (source.x == destination.x && source.y == destination.y) {
            return 0;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);
        grid[source.x][source.y] = true;
        int level = 0;
        int deltx[] = {1, 1, -1, -1, 2, 2, -2, -2};
        int delty[] = {2, -2, 2, -2, 1, -1, 1, -1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                Point poll = queue.poll();
//                grid[poll.x][poll.y] = true;
                for (int j = 0; j < deltx.length; j++) {
                    int x = poll.x + deltx[j];
                    int y = poll.y + delty[j];
                    if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
                        continue;
                    }
                    if (!grid[x][y]) {
                        if (x == destination.x && destination.y == y) {
                            return level;
                        }
                        grid[x][y] = true;
                        queue.offer(new Point(x, y));
                    }
                }

            }

        }

        return -1;
    }

    public static void main(String[] args) {
        boolean[][] grid = {
                {false, true, false},
                {false, false, true},
                {false, false, false}
        };
        SolutionLintCode611 solutionLintCode611 = new SolutionLintCode611();
        int i = solutionLintCode611.shortestPath(grid, new Point(2, 0), new Point(2, 2));
        System.out.println(i);
    }
}
