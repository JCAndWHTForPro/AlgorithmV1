package algorithm.lintcode;

import java.util.*;

/**
 * @ClassName: SolutionLintCode787
 * @Author: jicheng
 * @CreateDate: 2019/12/3 4:20 PM
 */
public class SolutionLintCode788 {

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
    public int shortestDistance(int[][] maze, int[] start, int[] destination){
        if (maze == null || start == null || destination == null) {
            return -1;
        }

        int dx = destination[0];
        int dy = destination[1];

        Queue<Cordinate> queue = new LinkedList<>();
        HashSet<Cordinate> visited = new HashSet<>();
        HashMap<Cordinate, Cordinate> mapping = new HashMap<>();

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
                    Cordinate cc = new Cordinate(cx, cy);
                    if (cx == dx && cy == dy) {
                        mapping.put(cc, poll);
                        return countStep(new Cordinate(cx, cy), cordinate, mapping);
                    }
                    if (visited.contains(cc)) {
                        continue;
                    }

                    queue.offer(cc);
                    visited.add(cc);
                    mapping.put(cc, poll);

                }
            }
        }


        return -1;

    }

    private int countStep(Cordinate last, Cordinate begin, HashMap<Cordinate, Cordinate> mapping) {
        int result = 0;

        while (last.x != begin.x || last.y != begin.y) {
            Cordinate cordinate = mapping.get(last);

            int xabs = Math.abs(last.x - cordinate.x);
            int yabs = Math.abs(last.y - cordinate.y);
            result += (xabs + yabs);

            last = cordinate;
        }


        return result;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] origin = {0, 4};
        int[] destinate = {4, 4};

        SolutionLintCode788 solutionLintCode787 = new SolutionLintCode788();
        int i = solutionLintCode787.shortestDistance(maze, origin, destinate);
        System.out.println(i);
    }


}
