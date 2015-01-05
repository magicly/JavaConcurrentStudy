package concurrentStudy;

import java.util.Calendar;

public class DateThread implements Runnable {

    DateUtilInterface dateUtil = null;

    public DateThread(DateUtilInterface dateUtil) {
        this.dateUtil = dateUtil;
    }

    public void run() {
        int year = 2000;
        Calendar cal;
        for (int i = 1; i < 100; i++) {
            System.out.println("no." + i);
            year++;
            cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
//            Date date = cal.getTime();
//            dateUtil.format(date);
            dateUtil.parse(year + "-05-25 11:21:21");
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
