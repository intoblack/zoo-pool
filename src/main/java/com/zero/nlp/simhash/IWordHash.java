package com.zero.nlp.simhash;

public interface IWordHash {

	public long hashCode(String word);

	public static class DefaultWordHash implements IWordHash {

		public long hashCode(String word) {
			if (word == null || word.length() == 0) {
				throw new IllegalArgumentException();
			}
			int b = 378551;
			int a = 63689;
			long hash = 0;
			for (int i = 0; i < word.length(); i++) {
				hash = hash * a + word.charAt(i);
				a = a * b;
			}
			return hash;
		}

	}
}
