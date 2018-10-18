package com.lj.czwgl.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
}
