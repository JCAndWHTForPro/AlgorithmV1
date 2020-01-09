package algorithm.business;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 用java实现：模拟饭堂M条队伍排队，N个窗口处理的场景。
 * 每隔一分钟输出每条队伍排队人数。
 *
 * @ClassName: QueueUp
 * @Author: jicheng
 * @CreateDate: 2020/1/9 3:25 PM
 */
public class QueueUp {

    private final static int PERSON_NUMBER = 22222;

    private volatile ArrayBlockingQueue<Integer> queue;

    private volatile List<List<Integer>> list;


    private int windows;

    public QueueUp(int queue, int windows) {


        this.windows = windows;

        this.queue = new ArrayBlockingQueue<>(queue);

        this.list = new CopyOnWriteArrayList<>();

        for (int q = 0; q < queue; q++) {
            this.queue.add(q);
            List<Integer> sublist = new CopyOnWriteArrayList<>();
            this.list.add(sublist);
            for (int i = 1; i < PERSON_NUMBER; i++) {
                sublist.add(i);
            }
        }

    }


    private class Th implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Integer token = queue.take();
                    List<Integer> rq = list.get(token.intValue());
                    if (!rq.isEmpty()) {
                        rq.remove(0);
                    }
                    queue.put(token);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private class CountTh implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(60L);
                    for (int i = 0; i < list.size(); i++) {
                        List<Integer> integers = list.get(i);
                        System.out.print("当前队列:" + i + "拥有人数：" + integers.size() + ";");
                    }
                    System.out.println();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        QueueUp queueUp = new QueueUp(4, 9);
        for (Integer i = 0; i < queueUp.windows; i++) {
            new Thread(queueUp.new Th()).start();
        }
        new Thread(queueUp.new CountTh()).start();
    }
}
