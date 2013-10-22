package com.circus.liran.zoopool.test;

import java.util.Set;

import ahocorasick.patternmatcher.IPatternMatcher;
import ahocorasick.patternmatcher.PatternMatcher;

public class TestString {
	public static void main(String[] args) {
		IPatternMatcher match_non = new PatternMatcher();
		match_non.addPattern("天使");
		Set<String> words = match_non.checkText("天x使");
		System.out.println(words);
	}
}
