package com.zero.nlp.simhash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * 
 * 
 * 
 * @author lixuze
 *
 */
public abstract class SimHash {

	private ISegment segment = null;
	private IWordHash wordHash = null;

	public SimHash(ISegment segment , IWordHash wordHash) {
		if (segment != null) {
			this.segment = segment;
		}
		if(wordHash != null)
		{
			this.wordHash = wordHash ;
		}
	}

	
	/**
	 * 
	 * ¼ÆËã×Ö·û´®µÄsimhash figuerprint 
	 * 
	 * 
	 * @param content
	 * @return
	 */
	public long simHash(String content) {
		long id = 0l;
		if (content != null && content.length() != 0) {
			List<String> words = segment.segment(content);
			Map<String, Long> wordsHash = this.wordsHash(words);
			Map<String, Integer> wordsWeight = wordsToWeight(words);
			int codes[] = new int[64];
			for (Entry<String, Long> word : wordsHash.entrySet()) {
				Bytes bytes = new Bytes(word.getValue());
				for (int i = 0; i < 64; i++) {
					codes[i] += (bytes.getByte(i) == 0 ? -wordsWeight.get(word
							.getKey()) : wordsWeight.get(word.getKey()));
				}
			}
			return arrayToHash(codes);

		}
		return id;
	}

	private long arrayToHash(int codes[]) {
		long hashCode = 0l;
		for (int i = 0; i < codes.length; i++) {
			if (codes[i] > 0) {
				hashCode |=  1 << i;
			}
		}
		return hashCode;
	}

	private Map<String, Long> wordsHash(List<String> words) {
		Map<String, Long> wordHashMap = new HashMap<String, Long>();
		for (String word : new HashSet<String>(words)) {
			wordHashMap.put(word, this.wordHash.hashCode(word));
		}
		return wordHashMap;
	}

	public abstract Map<String, Integer> wordsToWeight(List<String> words);

}
