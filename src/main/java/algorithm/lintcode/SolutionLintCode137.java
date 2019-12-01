package algorithm.lintcode;

import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * 克隆图，基础问题
 *
 * @ClassName: SolutionLintCode137
 * @Author: jicheng
 * @CreateDate: 2019/12/1 11:52 PM
 */
public class SolutionLintCode137 {


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        // 老节点->新节点的映射
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();

        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode poll = queue.poll();
            map.put(poll, new UndirectedGraphNode(poll.label));

            for (UndirectedGraphNode undirectedGraphNode : poll.neighbors) {
                if (!visited.contains(undirectedGraphNode)) {
                    queue.offer(undirectedGraphNode);
                    visited.add(undirectedGraphNode);
                }
            }
        }

        for (UndirectedGraphNode tnode : visited) {
            UndirectedGraphNode undirectedGraphNode = map.get(tnode);
            for (UndirectedGraphNode ttnode : tnode.neighbors) {
                UndirectedGraphNode newNode = map.get(ttnode);
                undirectedGraphNode.neighbors.add(newNode);
            }
        }


        return map.get(node);
    }


    public static void main(String[] args) {
        UndirectedGraphNode undirectedGraphNode1 = new UndirectedGraphNode(2);
        UndirectedGraphNode undirectedGraphNode2 = new UndirectedGraphNode(2);
        UndirectedGraphNode undirectedGraphNode3 = new UndirectedGraphNode(2);
        UndirectedGraphNode undirectedGraphNode4 = new UndirectedGraphNode(2);
        System.out.println(undirectedGraphNode1.hashCode() + "," + undirectedGraphNode1.toString());
        System.out.println(undirectedGraphNode2.hashCode() + "," + undirectedGraphNode2.toString());
        System.out.println(undirectedGraphNode3.hashCode() + "," + undirectedGraphNode3.toString());
        System.out.println(undirectedGraphNode4.hashCode() + "," + undirectedGraphNode4.toString());
    }
}
