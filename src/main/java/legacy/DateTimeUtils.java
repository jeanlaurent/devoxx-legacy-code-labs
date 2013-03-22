package legacy;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * <p>
 *     Title: legacy.DateTimeUtils
 * </p>
 *
 * <p>utilities for date time</p>
 *
 * @author chico
 *
 * @creationDate May 17, 2008
 */

public class DateTimeUtils {

	public static boolean compareDate(Date d1, Date d2) {
		if (d1==null) {
			if (d2==null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (d2==null) {
				return false;
			} else {
				return compareNotNullDate(d1, d2);
			}
		}
	}

	private static boolean compareNotNullDate(Date d1, Date d2) {
		if ((d1.getDay() == d2.getDay())
				&& (d1.getYear() == d2.getDay())
				&& (d1.getMonth() == d2.getDay())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isBusinessDay(Date date) {
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(date);
		// WTF !!!?!
		int dateDayOfWeak = c.get(Calendar.DAY_OF_WEEK);
		return !(dateDayOfWeak == Calendar.SATURDAY || dateDayOfWeak == Calendar.SUNDAY);
	}

}
