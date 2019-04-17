package algorithm;

import org.junit.Test;
import structure.MinPriorityQueue;

/**
 * @ClassName: TestMinPriorityQueue
 * @Author: jicheng
 * @CreateDate: 2019/3/31 下午6:38
 */
public class TestMinPriorityQueue {

    @Test
    public void addElemen() {
        MinPriorityQueue<Integer> queue = initQueue();
    }

    private MinPriorityQueue<Integer> initQueue() {
        MinPriorityQueue<Integer> queue = new MinPriorityQueue<>();
        queue.addElement(4);
        queue.addElement(23);
        queue.addElement(55);
        queue.addElement(41);
        queue.addElement(43);
        queue.addElement(14);
        queue.addElement(4);
        queue.addElement(23);
        queue.addElement(55);
        queue.addElement(41);
        queue.addElement(43);
        queue.addElement(14);
        queue.addElement(4);
        queue.addElement(23);
        queue.addElement(55);
        queue.addElement(41);
        queue.addElement(43);
        queue.addElement(14);
        System.out.println(queue);
        return queue;
    }

    @Test
    public void deleElement() {
        MinPriorityQueue<Integer> queue = initQueue();
        Integer minEle = queue.delElemet();
        System.out.println("堆顶的元素是：" + minEle);
        System.out.println("最新的堆中的元素是："+queue);

    }

}
