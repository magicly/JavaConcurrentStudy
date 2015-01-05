package concurrentStudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by magicalli on 2014/12/15.
 */
public class NoVisibility {
    private static boolean ready;
    private static long number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println("number:" + number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new Runnable() {
            @Override
            public void run() {
                while (!ready) {
                    System.out.println("pool number:" + number);
                }
                System.out.println("pool number:" + number);
            }
        });
        number = 42;
        ready = true;
        pool.shutdown();
    }

}
