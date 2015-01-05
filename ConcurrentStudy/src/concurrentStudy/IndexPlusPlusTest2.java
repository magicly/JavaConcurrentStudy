package concurrentStudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by magicalli on 2014/12/13.
 */
public class IndexPlusPlusTest2 {
    private static long n = 0;
    private static int m = 0;
    private volatile static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
//            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    atomicInteger.addAndGet(1);
                    try {
                        Thread.sleep(10);
//                        synchronized (IndexPlusPlusTest.class) {
                        synchronized (this) {
                            n += 1;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    }
                }
            }).start();
        }

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    m++;
                }
            });
        }
        pool.shutdown();
        Thread.sleep(1000);
        System.out.println("n:" + n);
        System.out.println("m:" + m);
        System.out.println("atomicInteger:" + atomicInteger);
    }
}
