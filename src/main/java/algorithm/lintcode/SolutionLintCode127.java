package algorithm.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基础的拓扑排序
 *
 * @ClassName: SolutionLintCode127
 * @Author: jicheng
 * @CreateDate: 2019/12/2 11:52 PM
 */
public class SolutionLintCode127 {

    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<>();


        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();

        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neightber : node.neighbors) {
                if (map.get(neightber) == null) {
                    map.put(neightber, 1);
                } else {
                    map.put(neightber, map.get(neightber) + 1);
                }
            }
        }

//        result.add(first);
        Queue<DirectedGraphNode> queue = new LinkedList<>();
//        queue.offer(first);
        for (DirectedGraphNode node : graph) {
            if (map.get(node) == null) {
                result.add(node);
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                DirectedGraphNode poll = queue.poll();
                for (DirectedGraphNode node : poll.neighbors) {
                    if (map.containsKey(node)) {
                        Integer integer = map.get(node) - 1;
                        if (integer <= 0) {
                            queue.offer(node);
                            result.add(node);
                        } else {
                            map.put(node, integer);
                        }

                    }
                }
            }
        }


        return result;
    }

}
