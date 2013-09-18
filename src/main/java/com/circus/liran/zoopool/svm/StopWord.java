package com.circus.liran.zoopool.svm;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hsqldb.lib.StringUtil;

import com.circus.liran.zoopool.exception.ZooPoolException;
import com.circus.liran.zoopool.util.Singleton;
import com.circus.liran.zoopool.util.ZooPoolUtil;

/**
 * 类功能： 停用词 类功能： 
 * #判断是否为停用词 
 * public boolean existStopWord(String); 
 * #获得所有停用词表
 * public Set<String> getStopSet(); 
 * #重新加载停用词文件 
 * public void load(String path)
 * 
 * @author lixuze
 * 
 */
public class StopWord {
	private Set<String> stopWord = new HashSet<String>();

	public StopWord(String path) throws ZooPoolException {
		load(path);
	}

	public void load(String path) throws ZooPoolException {
		if (StringUtil.isEmpty(path)) {
			throw new ZooPoolException("IS_EMPTY_PATH");
		}
		File file = new File(path);
		if (!(file.isFile() && file.exists())) {
			throw new ZooPoolException("IS_DIR_OR_NOT_EXIST");
		}
		this.stopWord.clear();
		List<String> fileContent = null;
		try {
			fileContent = ZooPoolUtil.readFileData(path);
		} catch (Exception e) {
			throw new ZooPoolException("READ_STOP_WORD_FILE_FAILED");
		}
		if (fileContent == null || fileContent.size() == 0) {
			throw new ZooPoolException("STOP_WORD_SET_EMPTY");
		}
		this.stopWord.addAll(fileContent);
	}

	public boolean existStopWord(String word) {
		if (StringUtil.isEmpty(word)) {
			return false;
		}
		return this.stopWord.contains(word);
	}

	public Set<String> getStopSet() {
		return this.stopWord;
	}
}
