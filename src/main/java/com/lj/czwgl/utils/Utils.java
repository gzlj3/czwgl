package com.lj.czwgl.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class Utils {
	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}

	public static boolean empty(String s) {
		return s == null || "".equals(s);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List convertIterToList(Iterable iter) {
		if (iter == null)
			return null;
		List result = new ArrayList<>();
		iter.forEach(obj -> {
			result.add(obj);
		});
		return result;
	}

	public static Integer getInteger(Integer value) {
		return value == null ? new Integer(0) : value;
	}

	public static Double getDouble(Double value) {
		return value == null ? new Double(0) : value;
	}

	public static Timestamp dateToTimestamp(Date rq) {
		return new Timestamp(rq.getTime());
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 返回2个日期之间的天数，date2 - date1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		int between_days = Math.round((date2.getTime() - date1.getTime())
				/ (1000 * 3600 * 24));
		return between_days;
	}

	/**
	 * 返回指定日期的相对日期
	 * 
	 * @param date
	 * @param field
	 *            (Calendar.DAY_OF_MONTH等)
	 * @param amount
	 * @return
	 */
	public static Date relativeDate(Date date, int field, int amount) {
		Calendar cd = Calendar.getInstance();

		cd.setTime(date);
		cd.add(field, amount);
		return cd.getTime();
	}

	public static LocalDate dateToLocalDate(Date date) {
		if (date == null)
			return null;
		// Instant instant = date.toInstant();
		Instant instant = Instant.ofEpochMilli(date.getTime());
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate = localDateTime.toLocalDate();
		return localDate;
	}

	public static Date localDateToDate(LocalDate localDate) {
		ZoneId zone = ZoneId.systemDefault();
		// Date date = new Date(localDate.toEpochDay());
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		Date date = Date.from(instant);
		return date;
	}

}
