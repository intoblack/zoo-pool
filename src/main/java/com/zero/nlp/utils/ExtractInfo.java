package com.zero.nlp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractInfo {

	public final static Pattern pattern = Pattern.compile("\\d+");

	/**
	 * 提取数字信息
	 * 
	 * 
	 * @param content 含有数字字符串
	 * @return 默认返回“0”
	 */
	public static String getNumbers(String content) {
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "0";
	}

}
