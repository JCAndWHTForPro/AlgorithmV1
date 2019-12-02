package algorithm.lintcode;

import java.util.ArrayList;

/**
 * @ClassName: DirectedGraphNode
 * @Author: jicheng
 * @CreateDate: 2019/12/2 11:52 PM
 */
public class DirectedGraphNode {
    public int label;
    public ArrayList<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }



}
