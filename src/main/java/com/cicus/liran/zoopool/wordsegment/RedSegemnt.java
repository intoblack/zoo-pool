package com.cicus.liran.zoopool.wordsegment;

import java.util.Set;


public class RedSegemnt
{
	
	public static String WordSegment(String str, Set<String> wordSet) {
		String splitStr = "";
		int maxlen = 10; // 切割长度 ，值越大 ，切分越准 ，但切分速度会变小
		boolean haveSplit = false; // 是否有切分词
		int noKnowStart = -1; // 分词结果中会遇到不认识的词，这个记录最后一次分词结果值
		for (int i = 0; i < str.length();) {
			int subLen = Math.min(maxlen, str.length() - i); // 获得切分长度，为了防止越界，将字符串长度考虑进来
			int j = subLen + i; // 相当于移动字符串，处理指针，越过处理完的
			for (; j > i;) { // 这个开始循环切分长度
				String tmp = str.substring(i, j); // 切割字符串
				if (wordSet.contains(tmp) == true) { // 是否在词典中
					if ((haveSplit == false) && (noKnowStart != -1)) // 如果以前没有切分
					{
						splitStr = splitStr + " "
								+ str.substring(noKnowStart, i); // 组合字符串
						noKnowStart = -1; // 置为-1 证明没有切分过
					}
					splitStr = splitStr + " " + tmp;
					i = j; // 因为到这里已经分词，将字符串的指针移动到现在已经处理完成
					haveSplit = true; // 设置为已经处理
					break;
				} else {
					j--;// 否则右移一个字符
				}
			}
			if (haveSplit == false) // 如果这个没有切分过
			{
				if (noKnowStart == -1) // 如果这个还没有初始化
				{
					noKnowStart = i;
				}
				i = i + 1;
			}
			haveSplit = false;
		}
		if (haveSplit == false && noKnowStart != -1) // 如果一句话最后那些词都不认识的话，切分
		{
			splitStr = splitStr + " " + str.substring(noKnowStart);
		}
		return splitStr.trim();
	}
	
}
