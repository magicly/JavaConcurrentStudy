package concurrentStudy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil01 implements DateUtilInterface {

    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void format(Date date) {
        System.out.println(dateformat.format(date));
    }

    public void parse(String str) {
        try {
            System.out.println(dateformat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
