package structure.graph;

import structure.IndexMinPriorityQueue;
import structure.graph.model.WordEdge;
import structure.graph.model.WordVector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Dijsktra
 * @Author: jicheng
 * @CreateDate: 2019/4/1 上午7:11
 */
public class Dijsktra {

    // 我们要求解的原图（邻接表存储）
    private Graph<WordVector, WordEdge> graph;

    // 索引小顶堆
    private IndexMinPriorityQueue<Integer> queue;

    // 保存每个节点是否被确认下来的映射，默认是false
    private Map<WordVector, Boolean> isMarked;

    // 保存源节点到每个节点的最小路径值
    private Number[] distTo;

    // 保存每个节点的最短路径是从那个邻边到达的
    private WordEdge[] from;

    public Number[] getDistTo() {
        return distTo;
    }

    public Dijsktra(Graph<WordVector, WordEdge> graph, WordVector src) {
        this.graph = graph;
        this.queue = new IndexMinPriorityQueue<>(graph.getVsize());
        this.isMarked = new HashMap<>();
        distTo = new Number[graph.getVsize()];
        from = new WordEdge[graph.getVsize()];
        // 默认将每个节点的确认映射，设置成false，就是都是未确认状态
        for (Map.Entry<WordVector, List<WordEdge>> entry : graph.getVectors().entrySet()) {
            WordVector key = entry.getKey();
            isMarked.put(key, false);
        }
        // 初始化最短路径保存数组，与最短路径的邻边数组
        for (int i = 0; i < graph.getVsize(); i++) {
            distTo[i] = 0.0;
            from[i] = null;
        }

        // 第一个将源节点加入到结构中
        from[src.getIndex()] = new WordEdge(src, src, 0);
        // 注意，加入的索引值与对应的结点，还有值是路径的长度
        this.queue.insert(src.getIndex(), from[src.getIndex()].getWeight());
        this.isMarked.put(src, true);

        // 开始遍历索引堆
        while (!this.queue.isEmpty()) {
            // 获取堆顶的元素索引
            Integer nodeIndex = this.queue.delElemetIndex();
            // 通过索引值与邻边数组，获取对应的当前遍历的堆顶定点
            WordVector v2 = from[nodeIndex].getV2();
            // 当前节点就是最短路径了，所以标记已被确认
            this.isMarked.put(v2, true);
            // 开始遍历当前节点的所有邻边
            List<WordEdge> edges = this.graph.getVectors().get(v2);
            for (WordEdge it : edges) {
                // 查询邻边另一边的结点，看看路径情况
                WordVector nextNode = it.getV2();
                if (!this.isMarked.get(nextNode)) {
                    int nextNodeIndex = nextNode.getIndex();
                    /**
                     *  核心：首先的if逻辑判断很关键，
                     *  看看当前节点索引对应的路径值有没有开始统计，
                     *  并且如果开始统计有值的话，就计算从当前邻边到
                     *  达当前节点的路径长度，是否小于已经存在的路径
                     *  如果小，就要更新，不小就略过。
                     */
                    if (from[nextNodeIndex] == null
                            || distTo[v2.getIndex()].intValue() + it.getWeight() < distTo[nextNodeIndex].intValue()) {

                        // 内部，表示要更新当前节点的最短路径，那就要改各个数组与索引堆中的值
                        distTo[nextNodeIndex] = distTo[v2.getIndex()].intValue() + it.getWeight();
                        from[nextNodeIndex] = it;
                        // 索引堆也有两种情况，有这个值，没这值
                        if (queue.contain(nextNodeIndex))
                            queue.change(nextNodeIndex,  distTo[nextNodeIndex].intValue());
                        else
                            queue.insert(nextNodeIndex, distTo[nextNodeIndex].intValue());
                    }
                }
            }
        }

    }
}
