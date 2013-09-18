package com.weidou.mota.zoopool.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 类名称： StatusExtractUtil
 * 类功能： 提取原创微博 表情，url，主题 ，@
 * 类方法：
 *      public static List<String> extractAt(String statusString)；
 *      提取 @ 
 *      
 *      public static List<String> extractEmtion(String statusString)；
 *      提取表情数目
 *      
 *      public static List<String> extractTopic(String statusString)；
 *      提取主题
 *      
 *      public static List<String> extractUrl(String statusString)；
 *      提取url
 *      
 *      
 *      public static String getClearString(String statusString,
 *			List<String> clearList)
 *		清除列表中
 *编写人：
 *      李旭泽
 * 
 * @author lixuze
 *
 */
public class StatusExtractUtil {
	private static final Pattern AT_PATTERN = Pattern.compile(
			"@[\u4E00-\u9FA5a-zA-Z0-9_]+", Pattern.UNICODE_CASE);
	private static final Pattern EMTION_PATTERN = Pattern.compile(
			"\\[.{0,10}?\\]", Pattern.UNICODE_CASE);
	private static final Pattern TOPIC_PATTERN = Pattern
			.compile("(#.*?#|【.*?】)");
	private static final Pattern URL_PATTERN = Pattern
			.compile("[http|https|www|ftp|ftps]+[://.]+[0-9A-Za-z:/[-]_#[?][=][.]]*");

	private static List<String> extract(Pattern pattern, String statusString) {
		List<String> matchList = new ArrayList<String>();
		Matcher matcher7 = pattern.matcher(statusString);
		while (matcher7.find()) {
			String matchString = matcher7.group(0);
			matchList.add(matchString);
		}
		return matchList;
	}

	/**
	 * 
	 * 
	 * @param statusString
	 * @return
	 */
	public static List<String> extractAt(String statusString) {
		return extract(AT_PATTERN, statusString);
	}

	public static List<String> extractEmtion(String statusString) {
		return extract(EMTION_PATTERN, statusString);
	}

	public static List<String> extractTopic(String statusString) {
		return extract(TOPIC_PATTERN, statusString);
	}

	public static List<String> extractUrl(String statusString) {
		return extract(URL_PATTERN, statusString);
	}

	public static String getClearString(String statusString,
			List<String> clearList) {
		for (String clearWord : clearList) {
			statusString = statusString.replace(clearWord, "");
		}
		return statusString;
	}

	public static void main(String args[]) {
//		for (String str : extractEmtion("#91问问小日记#今天做了2份属性问卷，2个幸运问卷没有回答成功。[酷库熊哭泣]")) {
//			System.out.println(str);
//		}
		System.out.println(getClearString("#91问问小日记#今天做了2份属性问卷，2个幸运问卷没有回答成功。[酷库熊哭泣]", extractEmtion("#91问问小日记#今天做了2份属性问卷，2个幸运问卷没有回答成功。[酷库熊哭泣]")));
	}
}
