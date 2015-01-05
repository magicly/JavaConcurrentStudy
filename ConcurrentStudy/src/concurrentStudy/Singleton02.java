package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class Singleton02 {
    private static Singleton02 singleton;

    private Singleton02() {
    }

    public static Singleton02 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton02();
        }
        return singleton;
    }
}
