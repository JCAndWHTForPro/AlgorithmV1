package algorithm.graph;

import algorithm.IndexMinPriorityQueue;
import algorithm.graph.model.WordEdge;
import algorithm.graph.model.WordVector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Dijsktra
 * @Author: jicheng
 * @CreateDate: 2019/4/1 上午7:11
 */
public class Dijsktra {

    private Graph<WordVector, WordEdge> graph;

    private IndexMinPriorityQueue<Integer> queue;

    private Map<WordVector, Boolean> isMarked;

    private WordVector src;

    private Number[] distTo;

    private WordEdge[] from;

    public Number[] getDistTo() {
        return distTo;
    }

    public Dijsktra(Graph<WordVector, WordEdge> graph, WordVector src) {
        this.graph = graph;
        this.src = src;
        this.queue = new IndexMinPriorityQueue<>(graph.getVsize());
        this.isMarked = new HashMap<>();
        distTo = new Number[graph.getVsize()];
        from = new WordEdge[graph.getVsize()];
        for (Map.Entry<WordVector, List<WordEdge>> entry : graph.getVectors().entrySet()) {
            WordVector key = entry.getKey();
            isMarked.put(key, false);
        }
        for (int i = 0; i < graph.getVsize(); i++) {
            distTo[i] = 0.0;
            from[i] = null;
        }

        from[src.getIndex()] = new WordEdge(src, src, 0);
        this.queue.insert(src.getIndex(), from[src.getIndex()].getWeight());
        this.isMarked.put(src, true);
        while (!this.queue.isEmpty()) {
            Integer nodeIndex = this.queue.delElemetIndex();
            WordVector v2 = from[nodeIndex].getV2();
            this.isMarked.put(v2, true);
            List<WordEdge> edges = this.graph.getVectors().get(v2);
            for (WordEdge it : edges) {
                WordVector nextNode = it.getV2();
                if (!this.isMarked.get(nextNode)) {
                    int nextNodeIndex = nextNode.getIndex();
                    if (from[nextNodeIndex] == null
                            || distTo[v2.getIndex()].intValue() + it.getWeight() < distTo[nextNodeIndex].intValue()) {
                        distTo[nextNodeIndex] = distTo[v2.getIndex()].intValue() + it.getWeight();
                        from[nextNodeIndex] = it;
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
