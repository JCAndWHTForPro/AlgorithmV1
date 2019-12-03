package algorithm.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 在迷宫中有一个球，里面有空的空间和墙壁。球可以通过滚上，下，左或右移动，
 * 但它不会停止滚动直到撞到墙上。当球停止时，它可以选择下一个方向。
 * <p>
 * 给定球的起始位置，目的地和迷宫，确定球是否可以停在终点。
 * <p>
 * 迷宫由二维数组表示。1表示墙和0表示空的空间。你可以假设迷宫的边界都是墙。开始和目标坐标用行和列索引表示。
 * <p>
 * 例1:
 * <p>
 * 输入:
 * map =
 * [
 * [0,0,1,0,0],
 * [0,0,0,0,0],
 * [0,0,0,1,0],
 * [1,1,0,1,1],
 * [0,0,0,0,0]
 * ]
 * start = [0,4]
 * end = [3,2]
 * 输出:
 * false
 * 例2:
 * <p>
 * 输入:
 * map =
 * [[0,0,1,0,0],
 * [0,0,0,0,0],
 * [0,0,0,1,0],
 * [1,1,0,1,1],
 * [0,0,0,0,0]
 * ]
 * start = [0,4]
 * end = [4,4]
 * 输出:
 * true
 * 注意事项
 * 1.在迷宫中只有一个球和一个目的地。
 * 2.球和目的地都存在于一个空的空间中，它们最初不会处于相同的位置。
 * 3.给定的迷宫不包含边框(比如图片中的红色矩形)，但是你可以假设迷宫的边界都是墙。
 * 5.迷宫中至少有2个空的空间，迷宫的宽度和高度都不会超过100。
 *
 * @ClassName: SolutionLintCode787
 * @Author: jicheng
 * @CreateDate: 2019/12/3 4:20 PM
 */
public class SolutionLintCode787 {

    public static class Cordinate {

        public int x;
        public int y;

        public Cordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cordinate cordinate = (Cordinate) o;
            return x == cordinate.x &&
                    y == cordinate.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }
    }

    /**
     * @param maze:        the maze
     * @param start:       the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null) {
            return false;
        }

        int dx = destination[0];
        int dy = destination[1];

        Queue<Cordinate> queue = new LinkedList<>();
        HashSet<Cordinate> visited = new HashSet<>();

        Cordinate cordinate = new Cordinate(start[0], start[1]);
        queue.offer(cordinate);
        visited.add(cordinate);

        int deltax[] = {1, -1, 0, 0};
        int deltay[] = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cordinate poll = queue.poll();
                for (int delta = 0; delta < deltax.length; delta++) {
                    int cx = poll.x;
                    int cy = poll.y;
                    int ex = deltax[delta];
                    int ey = deltay[delta];
                    while (cx + ex < maze.length && cx + ex >= 0
                            && cy + ey < maze[0].length && cy + ey >= 0
                            && maze[cx + ex][cy + ey] != 1) {
                        cx += ex;
                        cy += ey;
                    }
                    if (cx == dx && cy == dy) {
                        return true;
                    }
                    Cordinate cc = new Cordinate(cx, cy);
                    if (visited.contains(cc)) {
                        continue;
                    }

                    queue.offer(cc);
                    visited.add(cc);

                }
            }
        }


        return false;

    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0}
        };
        int[] origin = {0, 0};
        int[] destinate = {8, 6};

        SolutionLintCode787 solutionLintCode787 = new SolutionLintCode787();
        boolean b = solutionLintCode787.hasPath(maze, origin, destinate);
        System.out.println(b);
    }


}
