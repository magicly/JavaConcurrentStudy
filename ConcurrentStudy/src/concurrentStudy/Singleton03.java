package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class Singleton03 {
    private static Singleton03 singleton;

    private Singleton03() {
    }

    public synchronized static Singleton03 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton03();
        }
        return singleton;
    }
}
