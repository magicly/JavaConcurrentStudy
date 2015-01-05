package concurrentStudy;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by magicalli on 2014/12/13.
 */
public class SimpleDateFormatTest05 {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(dateTimeFormatter.parseDateTime("2014-12-19 11:21:21"));
                }
            }).start();
        }
    }
}
