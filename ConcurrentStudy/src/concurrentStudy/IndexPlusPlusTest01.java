package concurrentStudy;

import java.util.concurrent.CountDownLatch;

/**
 * Created by magicalli on 2014/12/13.
 */
public class IndexPlusPlusTest01 {
    private static final int N = 10;// 线程数
    private static final int M = 100000;//循环次数，太小的话（比如10）可能看不出来效果
    private volatile static int n = 0;//加volatile的目的是为了证明volatile没有“原子性”！

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(N);

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

        startGate.countDown();
        long t1 = System.currentTimeMillis();
        endGate.await();//等所有线程都结束之后才打印n，否则总是会打出错误的n
        long t2 = System.currentTimeMillis();
        System.out.println("cost time: " + (t2 - t1));
        System.out.println("n: " + n);
    }

}
