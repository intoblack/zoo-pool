package com.zero.nlp.trie;

public interface ITrie<T> {

	public void insert(T value);

	public boolean search(T value);

}
