package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class Singleton08 {
    private Singleton08() {
    }

    private static class SingletonHolder {
        private static final Singleton08 instance = new Singleton08();
    }

    public static Singleton08 getInstance() {
        return SingletonHolder.instance;
    }

    public void doXXX() {
        System.out.println("..............");
    }
}
