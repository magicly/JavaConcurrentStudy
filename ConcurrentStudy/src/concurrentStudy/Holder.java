package concurrentStudy;

/**
 * Created by magicalli on 2014/12/15.
 */
public class Holder {
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
//        System.out.println(n);
        if (n != n) {
            throw new AssertionError("what the fxxk!!!!!!");
        }
    }
}
