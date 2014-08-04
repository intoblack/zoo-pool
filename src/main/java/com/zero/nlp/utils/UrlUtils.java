package com.zero.nlp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {

	private UrlUtils() {

	}

	/**
	 * 抽取网址domain ，　比如www.baidu.com
	 * 
	 * 
	 * @param url
	 * @return
	 */
	public static String getDomain(String url) {
		final Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		if (!StringUtils.isEmpty(url)) {
			Matcher domain = p.matcher(url);
			if (domain.find()) {
				return domain.group(1);
			}
		}
		return "";
	}
	
	
	
	public static void main(String args[])
	{
		System.out.println(getDomain("http://www.cc.baidu.com/psdf/tt"));
	}
}
