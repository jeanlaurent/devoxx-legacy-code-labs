package legacy.dto;

import legacy.persistence.BaseDTO;

import java.util.Calendar;
import java.util.Date;

public class DateRange extends BaseDTO {

	private Date start;
	private Date end;

	private boolean compareDate(Date date1, Date date2) {
		if (date1==null) {
			if (date2==null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (date2==null) {
				return false;
			} else {
				return compareNotNullDate(date1, date2);
			}
		}
	}

	private boolean compareNotNullDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date2);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date1);
		if ((cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
				&& (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
				&& (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))) {
			return true;
		} else {
			return false;
		}
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
