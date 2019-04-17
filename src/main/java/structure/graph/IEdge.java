package structure.graph;

/**
 * @ClassName: IEdge
 * @Author: jicheng
 * @CreateDate: 2019/4/1 上午12:30
 */
public interface IEdge<V> {

    V getV1();

    V getV2();

    int getWeight();
}
