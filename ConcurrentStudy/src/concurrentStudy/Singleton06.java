package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class Singleton06 {
    private static volatile Singleton06 singleton;

    private Singleton06() {
    }

    public static Singleton06 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton06.class) {
                if (singleton == null) {
                    singleton = new Singleton06();
                }
            }
        }
        return singleton;
    }

    public void doXXX() {
        System.out.println("..............");
    }
}
