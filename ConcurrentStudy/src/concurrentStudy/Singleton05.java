package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class Singleton05 {
    private static Singleton05 singleton;// sth wrong?!!

    private Singleton05() {
    }

    public static Singleton05 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton05.class) {
                if (singleton == null) {
                    singleton = new Singleton05();
                }
            }
        }
        return singleton;
    }
}
