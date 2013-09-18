package com.circus.liran.zoopool.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	private double value;
	private Map<Character, TrieNode> wordMap = new HashMap<Character, TrieNode>();

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void addChild(Character word, TrieNode node) {
		this.wordMap.put(word, node);
	}

	public Map<Character, TrieNode> getWordMap() {
		return wordMap;
	}

	public void setWordMap(Map<Character, TrieNode> wordMap) {
		this.wordMap = wordMap;
	}
}
