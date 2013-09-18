package com.circus.liran.zoopool.svm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.circus.liran.zoopool.exception.ZooPoolException;
import com.circus.liran.zoopool.util.MapComparator;
import com.circus.liran.zoopool.util.ZooPoolUtil;

public class WordTrain {

	public static Map<String, Long> getWordID(String filePath)
			throws ZooPoolException {
		Map<String, Long> mapWordID = new HashMap<String, Long>();
		List<String> contents = ZooPoolUtil.readFileData(filePath);
		long id = 1000;
		for (String line : contents) {
			String wordArry[] = line.split("\t");
			mapWordID.put(wordArry[0], id);
			id++;
		}
		return mapWordID;
	}

	public static List<String> wordEncodeByID(List<String> contents,
			Map<String, Long> wordIDMap) {
		List<String> wordIDList = new ArrayList<String>();
		for (String line : contents) {
			String wordArry[] = line.split(" ");
			Map<Long, Long> mapWordIDCount = new HashMap<Long, Long>();
			for (String word : wordArry) {
				long id = wordIDMap.get(word);
				if (mapWordIDCount.containsKey(id)) {
					mapWordIDCount.put(id, mapWordIDCount.get(id) + 1);
				} else {
					mapWordIDCount.put(id, 1l);
				}
			}
			List<Map.Entry<Long, Long>> wordcountList = MapComparator
					.sortMap(mapWordIDCount);
			StringBuffer stringBuffer = new StringBuffer();
			boolean isFirst = true;
			for (Map.Entry<Long, Long> mapcount : wordcountList) {
				if (isFirst) {
					stringBuffer.append(mapcount.getKey() + ":"
							+ mapcount.getValue());
					isFirst = false;
				} else {
					stringBuffer.append(" " + mapcount.getKey() + ":"
							+ mapcount.getValue());
				}
			}
			wordIDList.add(stringBuffer.toString());
		}
		return wordIDList;

	}

	public static void main(String args[]) throws ZooPoolException {
		Map<String, Long> mapWordID = getWordID("/home/lixuze/traindata/wordrate.txt");
		Set<File> fileList = ZooPoolUtil.folderWalk("/home/lixuze/traindata/keyword");
		for(File file:fileList)
		{
			List<String> contents = ZooPoolUtil
					.readFileData("/home/lixuze/traindata/keyword/电池耐用.txt_seg_keyword");
			List<String> newcontents = wordEncodeByID(contents, mapWordID);
			String fileName = file.getParent() +"/"+ file.getName().split("\\.")[0] + ".train";
			try {
				ZooPoolUtil.writeFile(fileName, newcontents);
			} catch (IOException e) {
				System.out.println(e + "\t" + file.getAbsolutePath());
			}
		}
//		
//		wordEncodeByID(contents, mapWordID);
//		for (String word : mapWordID.keySet()) {
//			System.out.println(word + "\t\t" + mapWordID.get(word));
//		}
	}
}
