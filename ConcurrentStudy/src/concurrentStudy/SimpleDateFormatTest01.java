package concurrentStudy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by magicalli on 2014/12/13.
 */
public class SimpleDateFormatTest01 {
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(dateformat.parse("2014-12-19 11:21:21"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
