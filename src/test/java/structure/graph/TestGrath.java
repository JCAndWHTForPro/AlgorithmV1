package structure.graph;

import structure.graph.model.WordEdge;
import structure.graph.model.WordVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestGrath
 * @Author: jicheng
 * @CreateDate: 2019/4/1 上午12:23
 */
public class TestGrath {


    @Test
    public void createGraph() {
        List<WordVector> wordVectors = new ArrayList<>();
        WordVector a = new WordVector(0, "A");
        wordVectors.add(a);
        WordVector b = new WordVector(1, "B");
        wordVectors.add(b);
        WordVector c = new WordVector(2, "C");
        wordVectors.add(c);
        WordVector d = new WordVector(3, "D");
        wordVectors.add(d);
        WordVector e = new WordVector(4, "E");
        wordVectors.add(e);


        Graph<WordVector, WordEdge> graph = new Graph<>(wordVectors.toArray(new WordVector[0]));
        graph.addEdge(new WordEdge(a, b, 5));
        graph.addEdge(new WordEdge(b, c, 4));
        graph.addEdge(new WordEdge(c, d, 8));
        graph.addEdge(new WordEdge(d, c, 8));
        graph.addEdge(new WordEdge(d, e, 6));
        graph.addEdge(new WordEdge(a, d, 5));
        graph.addEdge(new WordEdge(c, e, 2));
        graph.addEdge(new WordEdge(e, b, 3));
        graph.addEdge(new WordEdge(a, e, 7));
        System.out.println(graph);
/*

        try {
            int distanceABC = distance(graph, Arrays.asList(a, b, c));
            System.out.println("a-b-c的距离是：" + distanceABC);
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }

        try {
            int distanceAD = distance(graph, Arrays.asList(a, d));
            System.out.println("a-d的距离是：" + distanceAD);
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }

        try {
            int distanceADC = distance(graph, Arrays.asList(a, d, c));
            System.out.println("a-d-c的距离是：" + distanceADC);
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }

        try {
            int distanceAEBCD = distance(graph, Arrays.asList(a, e, b, c, d));
            System.out.println("a-e-b-c-d的距离是：" + distanceAEBCD);
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }

        try {
            int distanceAED = distance(graph, Arrays.asList(a, e, d));
            System.out.println("a-e-d的距离是：" + distanceAED);
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }*/

        WordDepestPath wordDepestPath = new WordDepestPath(graph);
        List<List<WordEdge>> lists = wordDepestPath.depestPath(c, c);
        System.out.println("c到c最多有3站的数量是：" + lists.stream().filter(value -> value.size() <= 3).count());

        /*List<List<WordEdge>> lists2 = wordPath.depestPath4FourNode(a, c);
        System.out.println("a到c最必须要有4站的数量是：" + lists2.stream().filter(value -> value.size() == 4).count());


        Dijsktra dijsktra = new Dijsktra(graph, a);
        System.out.println("a-c的最短路径是：" + dijsktra.getDistTo()[c.getIndex()]);*/


    }

    private int distance(Graph<WordVector, WordEdge> graph, List<WordVector> vectors) {
        int result = 0;
        Map<WordVector, List<WordEdge>> vectorListMap = graph.getVectors();
        outer:
        for (int i = 0; i < vectors.size() - 1; i++) {
            WordVector it = vectors.get(i);
            WordVector nextIt = vectors.get(i + 1);
            List<WordEdge> edges = vectorListMap.get(it);
            if (edges == null) {
                throw new IllegalArgumentException("NO SUCH ROUTE");
            }
            inner:
            for (WordEdge edge : edges) {
                if (edge.getV2().equals(nextIt)) {
                    result += edge.getWeight();
                    continue outer;
                }
            }

            throw new IllegalArgumentException("NO SUCH ROUTE");
        }
        return result;
    }
}
