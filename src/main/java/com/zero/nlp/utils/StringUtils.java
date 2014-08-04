package com.zero.nlp.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

	private StringUtils() {

	}

	private static List<Integer> getSameWord(char word, String words, int last) {
		List<Integer> index = new ArrayList<Integer>();
		for (int i = last; i < words.length(); i++) {
			if (word == words.charAt(i)) {
				index.add(i);
			}
		}
		return index;
	}

	/**
	 * 
	 * 判断字符串是否为空　　
	 * 
	 * @param words
	 *            　待判断字符串
	 * @return　如果字符串为空返回ｔｒｕｅ　，　否则　，　返回false
	 */
	public static boolean isEmpty(String words) {
		if (words == null || words.length() == 0) {
			return true;
		}
		for (int i = 0; i < words.length(); i++) {
			if (!Character.isWhitespace(words.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 得到最大公共字符字串　
	 * 
	 * @param words1　字符串１　
	 * @param words2　字符串２
	 * @return　返回公共最大字符串序列　，　获得相同字串序列　，　没有排序
	 */
	public static List<String> getChildSequence(String words1, String words2) {
		if (isEmpty(words1) || isEmpty(words2)) {
			throw new IllegalArgumentException("字符串为空　！　无法得到子字符串！");
		}
		List<String> childs = new ArrayList<String>();
		int indexWords = 0;
		for (int i = 0; i < words1.length(); i++) {
			int end = i ;
			List<Integer> index = getSameWord(words1.charAt(i), words2,
					indexWords);
			for(Integer sameIndex : index)
			{
				while(end < words1.length() && indexWords < words2.length() && words1.charAt(end) ==  words2.charAt(sameIndex))
				{
					end++ ;
					sameIndex++ ;
					
				}
				int len = end - i ;
				if(end == i){
					continue ;
				}
				if(len >= 2)
				{
					childs.add(words1.substring(i , end)) ;
					i = end - 1;
					indexWords = sameIndex ;
				}
			}
		}
		return childs;

	}
	public static void main(String[] args) {
		System.out.println(getChildSequence("jjava程序员是一个吊丝职业", "java程序写出来很吊丝啊"));
	}

}
