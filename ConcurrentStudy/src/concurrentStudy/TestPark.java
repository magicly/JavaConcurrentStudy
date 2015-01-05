package concurrentStudy;

import sun.reflect.Reflection;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by magicalli on 2014/12/30.
 */
public class TestPark {
    static void f() {
        Class callerClass = Reflection.getCallerClass(1);
        System.out.println("ffff" + callerClass);
    }

    public static void t2() throws Exception {
        Thread t = new Thread(new Runnable() {
            private int count = 0;

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000) {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //等待或许许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(2000);

        // 中断线程
        t.interrupt();


        System.out.println("main over");
    }

    public static void main(String[] args) throws Exception {
        f();

        final Thread currentThread = Thread.currentThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("unpark start in " + Thread.currentThread());
                    TimeUnit.SECONDS.sleep(0);
                    LockSupport.unpark(currentThread);
                    System.out.println("unpark over in " + Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(currentThread);
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
//        LockSupport.unpark(currentThread);
        System.out.println("a.");
        LockSupport.park(TestPark.class);
        System.out.println("b.");
        LockSupport.park(TestPark.class);
        System.out.println("c.");

        t2();
    }
}
