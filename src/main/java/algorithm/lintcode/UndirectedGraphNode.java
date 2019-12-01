package algorithm.lintcode;

import java.util.ArrayList;

/**
 * @ClassName: UndirectedGraphNode
 * @Author: jicheng
 * @CreateDate: 2019/12/1 11:50 PM
 */
public class UndirectedGraphNode {
    public int label;
    public ArrayList<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
