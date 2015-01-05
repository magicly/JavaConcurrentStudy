package concurrentStudy;

import java.util.HashSet;
import java.util.Set;

public class SingletonTest {
    public static void main(String[] args) throws InterruptedException {
        final Set<Singleton02> sets = new HashSet<Singleton02>();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Singleton02 resource = Singleton02.getSingleton();
                    System.out.println(resource);
                    sets.add(resource);
                }
            }).start();
        }
        Thread.sleep(10000);

        System.out.println(sets.size());

        Singleton07.Instance.doXXX();
    }

}
