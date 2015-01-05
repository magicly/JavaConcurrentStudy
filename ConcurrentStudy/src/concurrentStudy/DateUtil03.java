package concurrentStudy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil03 implements DateUtilInterface {

    @Override
    public void format(Date date) {
        // 每次new一个对象，线程之间不共享
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateformat.format(date));
    }

    @Override
    public void parse(String str) {
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateformat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
