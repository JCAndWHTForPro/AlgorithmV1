package algorithm;

import org.junit.Test;
import structure.IndexMinPriorityQueue;

/**
 * @ClassName: TestMinPriorityQueue
 * @Author: jicheng
 * @CreateDate: 2019/3/31 下午6:38
 */
public class TestIndexMinPriorityQueue {

    @Test
    public void addElemen() {
        IndexMinPriorityQueue<Integer> queue = initQueue();
    }

    private IndexMinPriorityQueue<Integer> initQueue() {
        IndexMinPriorityQueue<Integer> queue = new IndexMinPriorityQueue<>();
        queue.addElement(55);
        queue.addElement(23);
        queue.addElement(4);
        queue.addElement(43);
        queue.addElement(14);
        queue.addElement(41);
        System.out.println(queue);
        return queue;
    }

    @Test
    public void deleElement() {
        IndexMinPriorityQueue<Integer> queue = initQueue();
        Integer minEle = queue.delElemet();
        System.out.println("\n堆顶的元素是：" + minEle);
        System.out.println("最新的堆中的元素是：\n"+queue);

    }

    @Test
    public void changeElem(){
        IndexMinPriorityQueue<Integer> queue = initQueue();
        queue.change(2,234);
        System.out.println("元素变换之后的数组是：\n"+queue);
    }

}
