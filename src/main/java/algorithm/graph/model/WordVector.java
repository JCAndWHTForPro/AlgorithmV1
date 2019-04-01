package algorithm.graph.model;

import java.util.Objects;

/**
 * @ClassName: Vector
 * @Author: jicheng
 * @CreateDate: 2019/4/1 上午12:33
 */
public class WordVector implements Comparable<WordVector>{

    private int index;

    private String word;

    public WordVector() {
    }

    public WordVector(int index, String word) {
        this.index = index;
        this.word = word;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordVector that = (WordVector) o;
        return index == that.index &&
                Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {

        return Objects.hash(index, word);
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int compareTo(WordVector o) {
        return this.getIndex() - o.getIndex();
    }
}
