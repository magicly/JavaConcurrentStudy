package concurrentStudy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil02 implements DateUtilInterface {

	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void format(Date date) {
		System.out.println(dateformat.format(date));
	}

	@Override
	public void parse(String str) {
		try {
			// 所有线程共享一个对象，但是加了同步
			synchronized(dateformat){
				System.out.println(dateformat.parse(str));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
