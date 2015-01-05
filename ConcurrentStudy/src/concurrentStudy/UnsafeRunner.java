package concurrentStudy;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UnsafeRunner {

    public static void main(String args[]) throws InterruptedException {

        int size = 10;

        CountDownLatch countDownLatch = new CountDownLatch(size);

        TomUnsafeRunner tomRunner = new TomUnsafeRunner();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 1; i <= size; i++) {
            executorService.execute(new Thread2RunUnsafe(countDownLatch, tomRunner, i + "_号"));
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    static class Thread2RunUnsafe implements Runnable {
        private CountDownLatch countDownLatch;
        private TomUnsafeRunner tomRunner;
        private String name;

        public Thread2RunUnsafe(CountDownLatch countDownLatch, TomUnsafeRunner tomRunner, String name) {
            this.countDownLatch = countDownLatch;
            this.tomRunner = tomRunner;
            this.name = name;
        }

        public void run() {
            System.out.println(this.name + ":running...");
            this.tomRunner.doWork();
            System.out.println(this.name + ":结束...");
            this.countDownLatch.countDown();

        }
    }

    static class TomUnsafeRunner {
        private int unsafeVar = 23;

        public void doWork() {
            ////////////////////一般写法///////////////
//            if (unsafeVar == 23) {
//                System.out.println("simple ==, 我判断出来了，unsafeVar ==23，我设置为46..");
//                try {
//                    //模拟业务代码
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                unsafeVar = 46;
//            }

            //用JAVA CAS技术
            Unsafe unsafe = UnsafeSupport.getInstance();
            Class clazz = TomUnsafeRunner.class;
            Field[] fields = clazz.getDeclaredFields();
            System.out.println("fieldName:fieldOffset");
            // 获取属性偏移量，可以通过这个偏移量给属性设置
            for (Field f : fields) {
                System.out.println(f.getName() + ":"
                        + unsafe.objectFieldOffset(f));
            }
            // arg0, arg1, arg2, arg3 分别是目标对象实例，目标对象属性偏移量，当前预期值，要设的值
            // unsafe.compareAndSwapInt(arg0, arg1, arg2, arg3)
            // 偏移量编译后一般不会变的,intParam这个属性的偏移量
            // unsafeVar:8
            long intParamOffset = 0;
            try {
                intParamOffset = unsafe.objectFieldOffset(this.getClass().getDeclaredField("unsafeVar"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            if (unsafe.compareAndSwapInt(this, intParamOffset, 23, 46)) {
                try {
                    ////模拟业务代码
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CAS, 我判断出来了，unsafeVar == 23，我设置为46..");
            }
        }
    }

    static class UnsafeSupport {
        private static Unsafe unsafe;

        static {
            Field field;
            try {
                // 由反编译Unsafe类获得的信息
                field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                // 获取静态属性,Unsafe在启动JVM时随rt.jar装载
                unsafe = (Unsafe) field.get(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static Unsafe getInstance() {
//            return Unsafe.getUnsafe();//没有用，只能native获取，否则会抛异常
            return unsafe;
        }
    }
}
