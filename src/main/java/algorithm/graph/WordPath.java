package algorithm.graph;

import algorithm.graph.model.WordEdge;
import algorithm.graph.model.WordVector;

import java.util.*;

/**
 * @ClassName: Path
 * @Author: jicheng
 * @CreateDate: 2019/4/1 上午1:01
 */
public class WordPath {

    private Graph<WordVector, WordEdge> graph;

    private Map<WordEdge, Boolean> isVisited;

    public WordPath(Graph<WordVector, WordEdge> graph) {
        this.graph = graph;
        this.isVisited = new HashMap<>();

        Map<WordVector, List<WordEdge>> vectors = graph.getVectors();

        for (Map.Entry<WordVector, List<WordEdge>> entry : vectors.entrySet()) {
            List<WordEdge> value = entry.getValue();
            for (WordEdge it : value) {
                isVisited.put(it, false);
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
            Stack<WordEdge> edgesResult = new Stack<>();
            edgesResult.push(edge);
            depestPath(edge, dest, edgesResult, result);
        }

        return result;
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
        if (v2.getWord().equals(dest.getWord())) {
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
        if (v2.getWord().equals(dest.getWord())) {
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
            depestPath4FourNode(it, dest,count+1, edgesResult, result);
        }
        edgesResult.pop();


    }
}
