package com.zero.nlp.simhash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultSimHash extends SimHash {

	public DefaultSimHash(ISegment segment , IWordHash wordHash) {
		super(segment , wordHash);
	}

	@Override
	public Map<String, Integer> wordsToWeight(List<String> words) {
		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		for (String word : words) {
			if (!wordCountMap.containsKey(word)) {
				wordCountMap.put(word, 0);
			}
			wordCountMap.put(word, wordCountMap.get(word) + 1);
		}
		return wordCountMap;
	}
	
	
	public static class DefaultSegment implements ISegment
	{

		public List<String> segment(String content) {
			List<String> words = new ArrayList<String>() ;
			Collections.addAll(words, content.split("\\s+"));
			return words;
		}
		
	}
	
	public static void main(String args[])
	{
		DefaultSimHash simHash = new DefaultSimHash(new DefaultSegment() , new IWordHash.DefaultWordHash()) ;
		System.out.println(simHash.simHash("中国 共产 党"));
		System.out.println(simHash.simHash("中国 共产 党"));
		System.out.println(simHash.simHash("莲花 白色 天使"));
	}

}
