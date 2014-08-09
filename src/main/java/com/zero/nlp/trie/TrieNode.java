package com.zero.nlp.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 词典树节点信息保存　，　主要原理是每个词典树　，　包含节点变量　和数值和子节点 主要保存结构　map
 * 
 * @author lixuze
 * 
 * @param <T>
 *            节点信息储存值　，　最好为int , char , double 等类型　
 */
public class TrieNode<T> {
	private double value;
	private Map<T, TrieNode<T>> wordMap = new HashMap<T, TrieNode<T>>();

	/**
	 * 得到当前节点的信息值 最好用double
	 * 
	 * 
	 * @return
	 */
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void addChild(T word, TrieNode<T> node) {
		this.wordMap.put(word, node);
	}

	public Map<T, TrieNode<T>> getWordMap() {
		return new HashMap<T, TrieNode<T>>(wordMap);
	}

	public void setWordMap(Map<T, TrieNode<T>> wordMap) {
		this.wordMap = wordMap;
	}

	public boolean containsChar(T ch) {
		if (ch == null) {
			return false;
		}
		if (wordMap.containsKey(ch)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 返回此此层节点中　，　某个特定值的子节点
	 * 
	 * @param ch
	 *            　节点的序号
	 * @return 指定值特定的序号的子节点
	 */
	public TrieNode<T> getTrieNode(T ch) {
		if (ch != null) {
			if (wordMap.containsKey(ch)) {
				return wordMap.get(ch);
			} else {
				TrieNode<T> node = new TrieNode<T>();
				this.addChild(ch, node);
				return wordMap.get(ch);

			}
		}
		throw new IllegalArgumentException("参数不能为null");
	}

	public TrieNode<T> contain(T ch) {
		if (ch != null) {
			if (wordMap.containsKey(ch)) {
				return wordMap.get(ch);
			}
		}
		return null;
	}
}
