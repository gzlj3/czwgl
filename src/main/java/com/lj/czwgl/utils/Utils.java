package com.lj.czwgl.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public final class Utils {
	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
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
