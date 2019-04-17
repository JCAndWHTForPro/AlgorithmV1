package structure.graph;

import structure.graph.model.WordEdge;
import structure.graph.model.WordVector;

import java.util.*;

/**
 * 图的深度优先遍历
 * @Author: jicheng
 * @CreateDate: 2019/4/1 上午1:01
 */
public class WordDepestPath {

    // 要遍历的图
    private Graph<WordVector, WordEdge> graph;

    // 记录当前边被访问到的次数
    private Map<WordEdge, Integer> edgeVisitedCount;

    // 记录当前顶点被访问到的次数
    private Map<WordVector, Integer> verticesVisitedCount;

    public WordDepestPath(Graph<WordVector, WordEdge> graph) {
        this.graph = graph;
        this.edgeVisitedCount = new HashMap<>();
        this.verticesVisitedCount = new HashMap<>();

        // 获取图的邻接表
        Map<WordVector, List<WordEdge>> vectors = graph.getVectors();
        // 遍历所有的边，进行初始化
        for (Map.Entry<WordVector, List<WordEdge>> entry : vectors.entrySet()) {
            List<WordEdge> value = entry.getValue();
            for (WordEdge it : value) {
                // 起始状态顶点和边都没有被访问到一次
                edgeVisitedCount.put(it, 0);
                verticesVisitedCount.put(it.getV1(), 0);
            }
        }


    }

    public List<List<WordEdge>> depestPath(WordVector src, WordVector dest) {
        List<List<WordEdge>> result = new ArrayList<>();
        Map<WordVector, List<WordEdge>> vectors = this.graph.getVectors();
        List<WordEdge> wordEdgesSrc = vectors.get(src);
        List<WordEdge> wordEdgesDest = vectors.get(dest);
        if (wordEdgesSrc == null || wordEdgesDest == null) {
            throw new IllegalArgumentException("没有此次搜索路径的结点");
        }
        for (WordEdge edge : wordEdgesSrc) {
            Stack<WordEdge> stack = new Stack<>();
            stack.push(edge);
            edgeVisitedCount.put(edge, edgeVisitedCount.get(edge) + 1);
            depestPathLargest3(edge, dest, 1, stack, result);

            edgeVisitedCount.put(stack.peek(), 0);
            stack.pop();
        }

        return result;
    }

    /**
     * 最多三站地的路径求解方法
     * 解决思路：
     * 如果整个路径上面最多只允许有三个顶点（除开起始节点）
     * 那么就是说，如果是一个有环有向图，那么这个解的边
     * 最多只能被访问一次。如果多被访问一次，那么就会超出
     * 三个顶点的题目要求，所以整个过程，重点要控制这个边
     * 的访问次数
     * @param currentEdge 当前遍历到的边
     * @param dest 目标顶点
     * @param largest 当前遍历到的站点的数量
     * @param stack 栈，用户辅助保存遍历节点
     * @param result 结果集，保存了所有符合条件的路径
     */
    private void depestPathLargest3(WordEdge currentEdge, WordVector dest, int largest,
                                    Stack<WordEdge> stack, List<List<WordEdge>> result) {
        // 递归终止条件
        if (edgeVisitedCount.get(stack.peek()).intValue() >= 2) {
            stack.pop();
            return;
        }

        WordVector v2 = currentEdge.getV2();
        // 注意这里：如果到了目的顶点，但是不满足最长路径值，同样不能成为结果
        if (v2.getData().equals(dest.getData()) && largest <= 3) {
            // 此分支，就是表示一条我们求解的路径
            ArrayList<WordEdge> rightPath = new ArrayList<>();
            result.add(rightPath);
            for (WordEdge it : stack) {
                rightPath.add(it);
            }
        }

        List<WordEdge> edges = this.graph.getVectors().get(v2);

        // 这种情况表示下个顶点没有临边，那就要栈顶的边不可走，要出栈
        if (edges.isEmpty()) {
            stack.pop();
            return;
        }

        // 对当前层遍历到的边的结束顶点，进行邻接边的遍历
        for (WordEdge it : edges) {
            // 每每遍历到了一个邻接边，要进行入栈+计数
            stack.push(it);
            edgeVisitedCount.put(it, edgeVisitedCount.get(it) + 1);
            // 开始递归
            depestPathLargest3(it, dest, largest + 1, stack, result);
        }

        /**
         *  注意这里，走到了这个地方，表示遍历邻边已经结束，
         *  每次弹出一个值的之前，要把这个邻边对应的访问计数
         *  清零，为了不影响后面的递归结束判断
         */
        edgeVisitedCount.put(stack.peek(), 0);
        stack.pop();

    }

    public List<List<WordEdge>> depestPath4FourNode(WordVector src, WordVector dest) {
        List<List<WordEdge>> result = new ArrayList<>();
        Map<WordVector, List<WordEdge>> vectors = this.graph.getVectors();
        List<WordEdge> wordEdgesSrc = vectors.get(src);
        List<WordEdge> wordEdgesDest = vectors.get(dest);
        if (wordEdgesSrc == null || wordEdgesDest == null) {
            throw new IllegalArgumentException("没有此次搜索路径的结点");
        }
        for (WordEdge edge : wordEdgesSrc) {
            Stack<WordEdge> edgesResult = new Stack<>();
            edgesResult.push(edge);
            depestPath4FourNode(edge, dest, 1, edgesResult, result);
        }

        return result;
    }

    private void depestPath(WordEdge edge, WordVector dest, Stack<WordEdge> edgesResult, List<List<WordEdge>> result) {
        WordVector v2 = edge.getV2();
        if (v2.getData().equals(dest.getData())) {
            ArrayList<WordEdge> path = new ArrayList<>();
            result.add(path);
            for (WordEdge it : edgesResult) {
                path.add(it);
            }
            return;
        }

        List<WordEdge> edges = this.graph.getVectors().get(v2);
        if (edges.isEmpty()) {
            edgesResult.pop();
            return;
        }


        for (WordEdge it : edges) {
            edgesResult.push(it);
            // 开始递归
            depestPath(it, dest, edgesResult, result);
        }
        edgesResult.pop();


    }

    private void depestPath4FourNode(WordEdge edge, WordVector dest, int count, Stack<WordEdge> edgesResult, List<List<WordEdge>> result) {
        if (count > 4) {
            edgesResult.pop();
            return;
        }
        WordVector v2 = edge.getV2();
        if (v2.getData().equals(dest.getData())) {
            ArrayList<WordEdge> path = new ArrayList<>();
            result.add(path);
            for (WordEdge it : edgesResult) {
                path.add(it);
            }
        }

        List<WordEdge> edges = this.graph.getVectors().get(v2);
        if (edges.isEmpty()) {
            edgesResult.pop();
            return;
        }


        for (WordEdge it : edges) {
            edgesResult.push(it);
            // 开始递归
            depestPath4FourNode(it, dest, count + 1, edgesResult, result);
        }
        edgesResult.pop();


    }
}
