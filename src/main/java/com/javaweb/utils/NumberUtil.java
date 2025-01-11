package com.javaweb.utils;

public class NumberUtil {
	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
