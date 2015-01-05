package concurrentStudy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil04 implements DateUtilInterface {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 每个线程一个对象，线程之间不共享
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };

    public DateFormat getDateFormat() {
        return threadLocal.get();
    }

    @Override
    public void parse(String textDate) {

        try {
            System.out.println(getDateFormat().parse(textDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void format(Date date) {
        System.out.println(getDateFormat().format(date));
    }
}
