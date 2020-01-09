package algorithm.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 初始化一副扑克52张，随机发给四个人，不能用random
 *
 * @ClassName: SqueezerSendout
 * @Author: jicheng
 * @CreateDate: 2020/1/9 2:40 PM
 */
public class SqueezerSendout {


    private volatile ArrayBlockingQueue<Person> blockingQueue;

    private List<Squeezer> squeezers;

    private class Person {
        private Integer number;

        private List<Squeezer> squeezersHold;

        public Person(Integer number, List<Squeezer> squeezersHold) {
            this.number = number;
            this.squeezersHold = squeezersHold;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public List<Squeezer> getSqueezersHold() {
            return squeezersHold;
        }

        public void setSqueezersHold(List<Squeezer> squeezersHold) {
            this.squeezersHold = squeezersHold;
        }
    }

    private class Squeezer {
        private Integer number;

        // 1:红桃,2:黑桃,3:方片,4:梅花
        private Integer color;

        private Boolean boss;


        Squeezer(Integer number, Integer color, Boolean boss) {
            this.number = number;
            this.color = color;
            this.boss = boss;
        }
    }


    public SqueezerSendout() {

        this.squeezers = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 1; j <= 4; j++) {
                this.squeezers.add(new Squeezer(i, j, null));
            }
        }
//        this.squeezers.add(new Squeezer(null, null, false));
//        this.squeezers.add(new Squeezer(null, null, true));


        this.blockingQueue = new ArrayBlockingQueue(4);
        for (int i = 1; i <= 4; i++) {
            this.blockingQueue.offer(new Person(i, new ArrayList<>()));
        }
    }


    private class Th implements Runnable{

        private Squeezer squeezer;

        public Th(Squeezer squeezer) {
            this.squeezer = squeezer;
        }

        @Override
        public void run(){
            try {
                Person poll = blockingQueue.take();
                poll.getSqueezersHold().add(this.squeezer);
                blockingQueue.put(poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public ArrayBlockingQueue<Person> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(ArrayBlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public List<Squeezer> getSqueezers() {
        return squeezers;
    }

    public void setSqueezers(List<Squeezer> squeezers) {
        this.squeezers = squeezers;
    }

    public static void main(String[] args) {
        SqueezerSendout squeezerSendout = new SqueezerSendout();
        for(Squeezer squeezer : squeezerSendout.getSqueezers()){
            Th th = squeezerSendout.new Th(squeezer);
            new Thread(th).start();
        }

        System.out.println(squeezerSendout.getSqueezers());
    }


}
