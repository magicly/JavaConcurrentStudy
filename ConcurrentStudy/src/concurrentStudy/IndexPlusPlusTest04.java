package concurrentStudy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by magicalli on 2014/12/13.
 */
public class IndexPlusPlusTest04 {
    private static final int N = 100;
    private static final int M = 100000;
    private volatile static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(N);

        for (int i = 0; i < N; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < M; j++) {
                        atomicInteger.addAndGet(1);
                    }

                    endGate.countDown();
                }
            }).start();
        }

        long t1 = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long t2 = System.currentTimeMillis();
        System.out.println("cost time: " + (t2 - t1));
        System.out.println("atomicInteger:" + atomicInteger);
    }

}
