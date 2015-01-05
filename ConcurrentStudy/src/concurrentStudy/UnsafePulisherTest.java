package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class UnsafePulisherTest {
    public Holder holder;

    public void init() {
        holder = new Holder(42);
    }

    public static void main(String[] args) {
        final UnsafePulisherTest test = new UnsafePulisherTest();
        test.init();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.holder.assertSanity();
            }
        }).start();
    }
}
