package algorithm.lintcode;

import java.util.*;

/**
 * 给定一张 无向图，一个 节点 以及一个 目标值，返回距离这个节点最近 且 值为目标值的节点，如果不能找到则返回 NULL。
 * 在给出的参数中, 我们用 map 来存节点的值
 * <p>
 * 例1:
 * <p>
 * 输入:
 * {1,2,3,4#2,1,3#3,1,2#4,1,5#5,4}
 * [3,4,5,50,50]
 * 1
 * 50
 * 输出:
 * 4
 * <p>
 * 解释:
 * 2------3  5
 * \     |  |
 * \    |  |
 * \   |  |
 * \  |  |
 * 1 --4
 * Give a node 1, target is 50
 * <p>
 * there a hash named values which is [3,4,10,50,50], represent:
 * Value of node 1 is 3
 * Value of node 2 is 4
 * Value of node 3 is 10
 * Value of node 4 is 50
 * Value of node 5 is 50
 * <p>
 * TODO jicheng 这个好做
 *
 * @ClassName: SolutionLintCode618
 * @Author: jicheng
 * @CreateDate: 2019/12/4 12:32 AM
 */
public class SolutionLintCode618 {

    /*
     * @param graph: a list of Undirected graph node
     * @param values: a hash mapping, <UndirectedGraphNode, (int)value>
     * @param node: an Undirected graph node
     * @param target: An integer
     * @return: a node
     */
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {

        if (graph == null || node == null || values == null) {
            return null;
        }

        HashSet<UndirectedGraphNode> visited = new HashSet<>();

        Queue<UndirectedGraphNode> queue = new LinkedList<>();

        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode poll = queue.poll();
                if (values.get(poll) != null && values.get(poll).intValue() == target) {
                    return poll;
                }

                for (UndirectedGraphNode ne : poll.neighbors) {
                    if (visited.contains(ne)) {
                        continue;
                    }

                    queue.offer(ne);

                    visited.add(ne);
                }
            }


        }
        return null;
    }
}
