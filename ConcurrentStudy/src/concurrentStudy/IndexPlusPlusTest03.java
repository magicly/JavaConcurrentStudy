package concurrentStudy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by magicalli on 2014/12/13.
 */
public class IndexPlusPlusTest03 {
    private static final int N = 100;
    private static final int M = 100000;
    private static int n = 0;
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

                    synchronized (IndexPlusPlusTest03.class) {
                        for (int j = 0; j < M; j++) {
                            n += 1;
                        }
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
        System.out.println("n:" + n);
        System.out.println("atomicInteger:" + atomicInteger);
    }

}
