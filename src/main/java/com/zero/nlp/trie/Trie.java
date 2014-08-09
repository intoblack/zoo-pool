package com.zero.nlp.trie;

import com.zero.nlp.utils.StringUtils;

public class Trie implements ITrie<String> {
	private TrieNode<Character> rootNode = new TrieNode<Character>();

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("abccdad");
		trie.insert("adfdfss");
		System.out.println(trie.search("abccdad"));
		System.out.println(trie.search("abbb"));
	}

	@Override
	public void insert(String value) {
		if (StringUtils.isEmpty(value)) {
			throw new IllegalArgumentException();
		}
		TrieNode<Character> currentNode = rootNode;
		for (int i = 0; i < value.length(); i++) {
			Character ch = value.charAt(i);
			currentNode = currentNode.getTrieNode(ch);
		}
	}

	@Override
	public boolean search(String value) {
		if (StringUtils.isEmpty(value)) {
			throw new IllegalArgumentException();
		}
		TrieNode<Character> currentNode = rootNode;
		for (int i = 0; i < value.length(); i++) {
			Character ch = value.charAt(i);
			currentNode = currentNode.contain(ch);
			if (currentNode == null) {
				return false;
			}
		}
		return true;
	}
}
