package structure.graph.model;

import structure.graph.IEdge;

import java.util.Objects;

/**
 * @ClassName: WordEdge
 * @Author: jicheng
 * @CreateDate: 2019/4/1 上午12:35
 */
public class WordEdge implements IEdge<WordVector>, Comparable<WordEdge> {

    private WordVector v1, v2;

    private int weight;

    public WordEdge() {
    }

    public WordEdge(WordVector v1, WordVector v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public WordVector getV1() {
        return v1;
    }

    @Override
    public WordVector getV2() {
        return v2;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordEdge wordEdge = (WordEdge) o;
        return weight == wordEdge.weight &&
                Objects.equals(v1, wordEdge.v1) &&
                Objects.equals(v2, wordEdge.v2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(v1, v2, weight);
    }


    @Override
    public String toString() {
        return "WordEdge{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(WordEdge o) {
        return this.weight - o.getWeight();
    }
}
