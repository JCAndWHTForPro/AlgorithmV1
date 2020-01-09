package algorithm.business;

/**
 * @ClassName: ThreadArrays
 * @Author: jicheng
 * @CreateDate: 2020/1/9 2:25 PM
 */
public class ThreadArrays {


    private static volatile Object lock = new Object();


    private static class Array1Thread implements Runnable {

        private int[] arr = {1, 3, 5, 7, 9};

        @Override
        public void run() {

            commonArr(arr);
        }

    }

    private static void commonArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            try {
                synchronized (lock) {
                    lock.notify();
                    lock.wait(100L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Array2Thread implements Runnable {

        private int[] arr = {2, 4, 6, 8, 10};

        @Override
        public void run() {
            commonArr(arr);
        }
    }


    public static void main(String[] args) {
        new Thread(new Array1Thread()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Array2Thread()).start();
    }
}
