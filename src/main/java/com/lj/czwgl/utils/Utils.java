package com.lj.czwgl.utils;

import java.util.UUID;

public final class Utils {
	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString().replace("-", "")
				.toUpperCase();
		return uuid;
	}
}
