package algorithm.graph;

import java.util.*;

/**
 * 直接实现有向图
 *
 * @ClassName: Graph
 * @Author: jicheng
 * @CreateDate: 2019/3/31 下午11:37
 */
public class Graph<V, E extends IEdge<V>> {

    private int vsize;

    private int esize;

    private Map<V, List<E>> vectors;

    public Graph() {
        this.vsize = 0;
        this.esize = 0;
        this.vectors = new HashMap<>();

    }

    public Graph(V[] datas) {
        if (Objects.isNull(datas) || datas.length == 0) {
            throw new IllegalArgumentException("初始化失败");
        }
        this.vsize = datas.length;
        this.esize = 0;
        this.vectors = new HashMap<>();
        for (int i = 0; i < this.vsize; i++) {
            this.vectors.put(datas[i], new LinkedList<>());
        }
    }


    public void addEdge(E w) {
        List<E> ts = this.vectors.get(w.getV1());
        if (ts == null) {
            throw new IllegalArgumentException("这个节点不存在");
        }
        if (!ts.contains(w)) {
            ts.add(w);
            this.esize++;
        }


    }

    // 获取总节点数
    public int getVsize() {
        return vsize;
    }

    // 获取总边数
    public int getEsize() {
        return esize;
    }

    public Map<V, List<E>> getVectors() {
        return vectors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, List<E>> entry : this.vectors.entrySet()) {
            V key = entry.getKey();
            List<E> value = entry.getValue();
            sb.append(key + ":");
            for (int i = 0; i < value.size(); i++) {
                E e = value.get(i);
                V v2 = e.getV2();
                sb.append(v2+"(").append(e.getWeight()).append(")");
                if (i < value.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
