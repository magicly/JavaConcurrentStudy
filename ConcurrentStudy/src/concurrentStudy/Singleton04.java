package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class Singleton04 {
    private static Singleton04 singleton;

    private Singleton04() {
    }

    public static Singleton04 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton04.class) {
                singleton = new Singleton04();
            }
        }
        return singleton;
    }
}
