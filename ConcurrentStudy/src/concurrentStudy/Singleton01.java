package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class Singleton01 {
    private static Singleton01 singleton = new Singleton01();

    private Singleton01() {
        // 也许有一些很费时的操作，有可能不会用到singleton
    }

    public static Singleton01 getSingleton() {
        return singleton;
    }
}
