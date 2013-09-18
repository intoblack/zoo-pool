package com.weidou.com.zoopool.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoHtml {
	
	
	/**
	 * 格式化html
	 * 
	 * @param html
	 * @return
	 */
	public static String stripHTML(String html) {
		String noHTMLString = "";
		Matcher m = Pattern.compile("&#(\\d+);", Pattern.CASE_INSENSITIVE).matcher(html);
		boolean b = false;
		while (m.find()) {
			html = html.replace("&#" + m.group(1) + ";", (char) Integer.parseInt(m.group(1)) + "");
			b = true;
		}
		if (!b) {
			m = Pattern.compile("&#x([\\da-f]+);", Pattern.CASE_INSENSITIVE).matcher(html);
			while (m.find()) {
				html = html.replaceAll("&#[x|X]" + m.group(1) + ";", (char) Integer.parseInt(m.group(1), 16) + "");
			}
		}// 上面的那一段是处理&#X4E2D;之类的代码段
		noHTMLString = html.replaceAll("<\\s*(?:br|Br|BR|bR)\\s*(?:/)*\\s*>", "\n");
		noHTMLString = noHTMLString.replaceAll("　", " ");
		noHTMLString = noHTMLString.replaceAll("", " ");
		noHTMLString = noHTMLString.replaceAll("&nbsp;", " ");
		noHTMLString = noHTMLString.replaceAll("\\<.*?\\>", "");
		noHTMLString = stringReplace(noHTMLString, "　", " ");
		noHTMLString = stringReplace(noHTMLString, "", " ");
		return noHTMLString.trim();
	}

	private static String stringReplace(String noHTMLString, String string,
			String string2) {
		return null;
	}


}
