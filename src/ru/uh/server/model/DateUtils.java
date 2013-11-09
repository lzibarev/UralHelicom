package ru.uh.server.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static final String FORMAT = "yyyy/MM/dd";
	private static final SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
	private static final long milisecondsInDay = 24 * 60 * 60 * 1000;
	private static final Calendar c = Calendar.getInstance();

	public static Date getDateNoTime(Date date) {
		return getDate(formatter.format(date));
	}

	public static Date getDate(String dateStr) {
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return null;
	}

	public static int diffInDays(Date from, Date to) {
		long diff = to.getTime() - from.getTime();
		long diffDays = diff / milisecondsInDay;
		return (int) diffDays;
	}

	public static Date getOffsetDate(Date date, int days) {
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
}
