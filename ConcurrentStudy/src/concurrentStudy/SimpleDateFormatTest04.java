package concurrentStudy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by magicalli on 2014/12/13.
 */
public class SimpleDateFormatTest04 {

    private static final ThreadLocal<SimpleDateFormat> dateformat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            System.out.println("init.......");
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        try {
                            System.out.println(dateformat.get().parse("2014-12-19 11:21:21"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
