package concurrentStudy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by magicalli on 2014/12/13.
 */
public class IndexPlusPlusTestAll {
    private static final int N = 10;// 线程数
    private static final int M = 100000;//循环次数，太小的话（比如10）可能看不出来效果
    private volatile static int n = 0;//加volatile的目的是为了证明volatile没有“原子性”！
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        testAll(1);
        testAll(2);
        testAll(3);
        testAll(4);
    }

    public static void testAll(int i) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(N);

        switch (i) {
            case 1:
                test1(startGate, endGate);
                break;
            case 2:
                test2(startGate, endGate);
                break;
            case 3:
                test3(startGate, endGate);
                break;
            case 4:
                test4(startGate, endGate);
                break;
        }

        startGate.countDown();
        long t1 = System.currentTimeMillis();
        endGate.await();//等所有线程都结束之后才打印n，否则总是会打出错误的n
        long t2 = System.currentTimeMillis();
        System.out.println("test" + i + " cost time: " + (t2 - t1));
        if (i == 4) {
            n = atomicInteger.get();
        }
        System.out.println("n: " + n);
        n = 0;
    }

    private static void test1(final CountDownLatch startGate, final CountDownLatch endGate) {
        for (int i = 0; i < N; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();//所有线程同时真正开始运行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < M; j++) {
                        n += 1;
                    }

                    endGate.countDown();
                }
            }).start();
        }
    }

    private static void test2(final CountDownLatch startGate, final CountDownLatch endGate) {
        for (int i = 0; i < N; i++) {
            new Thread(new Runnable() {
                @Override
                public synchronized void run() {// 错误地在不同的Rannable匿名类上synchronized
                    try {
                        startGate.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < M; j++) {
                        n += 1;
                    }

                    endGate.countDown();
                }
            }).start();
        }
    }

    private static void test3(final CountDownLatch startGate, final CountDownLatch endGate) {
        for (int i = 0; i < N; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (IndexPlusPlusTest03.class) {// 所有线程应该加同一个锁，很奇怪，这个居然是最快的！
                        for (int j = 0; j < M; j++) {
                            n += 1;
                        }
                    }

                    endGate.countDown();
                }
            }).start();
        }
    }

    private static void test4(final CountDownLatch startGate, final CountDownLatch endGate) {
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
                        atomicInteger.incrementAndGet();
                    }

                    endGate.countDown();
                }
            }).start();
        }
    }
}
