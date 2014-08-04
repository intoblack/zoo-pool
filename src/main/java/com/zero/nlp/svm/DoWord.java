package com.zero.nlp.svm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.zero.nlp.exception.ZooPoolException;
import com.zero.nlp.utils.ZooPoolUtil;

public class DoWord {
	private final static Logger logger = Logger.getLogger(DoWord.class);
	private StopWord stopword = null;
	private Map<String, Long> wordRateMap = null;

	public DoWord(String stopWordPath) throws ZooPoolException {
		stopword = new StopWord(stopWordPath);
		wordRateMap = new HashMap<String, Long>();
	}

	public List<String> clearwordWithSpace(String words)
			throws ZooPoolException {
		return clearwordBySplit(words, " ");
	}

	public List<String> clearwordBySplit(String words, String splitWord)
			throws ZooPoolException {
		if (splitWord == null) {
			logger.info("splitWord:" + splitWord);
			throw new ZooPoolException("WORDS_IS_EMPTY_OR_SPLITWORD_IS_EMPTY");
		}
		List<String> wordList = new ArrayList<String>();
		for (String word : words.split(splitWord)) {
			if (!stopword.existStopWord(word)) {
				wordList.add(word);
			} else {
				logger.info("clear :" + word);
			}
		}
		return wordList;
	}

	private void insertWord(String Word) {
		if (Word != null) {
			if (wordRateMap.containsKey(Word)) {
				wordRateMap.put(Word, wordRateMap.get(Word) + 1);
			} else {
				wordRateMap.put(Word, 1l);
			}
		}
	}

	public Map<String, Long> getWordRate() {
		return this.wordRateMap;
	}

	public Set<File> loadfile(String folderPath) throws ZooPoolException {
		File file = new File(folderPath);
		Set<File> fileSet = new HashSet<File>();
		if (file.isDirectory()) {
			fileSet.addAll(ZooPoolUtil.folderWalk(folderPath));
		} else if (file.isFile()) {
			fileSet.add(file);
		} else {
			throw new ZooPoolException("NO_FOLDER_OR_FILE");
		}
		return fileSet;
	}

	public void work(String folderPath) throws ZooPoolException, com.zero.nlp.utils.ZooPoolException  {
		Set<File> fileSet = loadfile(folderPath);
		for (File file : fileSet) {
			if (file.isDirectory()) {
				continue;
			}
			List<String> contents = ZooPoolUtil.readFileData(file);
			List<String> newContents = new ArrayList<String>();
			for (String line : contents) {
				List<String> wordList = clearwordWithSpace(line);
				for (String word : wordList) {
					insertWord(word);
				}
				newContents.add(ZooPoolUtil.wordListToString(wordList, " ")); // 组装成新闻件名称
			}
			String newFileName = file.getAbsolutePath() + "_keyword";
			try {
				ZooPoolUtil.writeFile(newFileName, newContents);
			} catch (IOException e) {
				logger.info("写文件失败：" + file.getName());
			}
		}
		List<String> contents = new ArrayList<String>();
		for (String key : wordRateMap.keySet()) {
			contents.add(key + "\t" + wordRateMap.get(key));
		}
		try {
			ZooPoolUtil.writeFile("/home/lixuze/traindata/wordrate.txt",
					contents);
		} catch (IOException e) {
			logger.info("单词次数文件，写文件失败");
		}
	}

	public static void main(String args[]) {

	}

}
