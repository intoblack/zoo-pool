package com.circus.liran.zoopool.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
/**
 * 
 * @classname public class MapComparator;
 * 
 * @class_function public static List<Map.Entry<Long, Long>> sortMap(
			Map<Long, Long> mapWordIDCount)
			
 *    @function 对特定map进行排序
 * 
 * 
 * @author lixuze
 *
 */
public class MapComparator {

	/**
	 * 
	 * 
	 * @param mapWordIDCount
	 * @return
	 */
	public static List<Map.Entry<Long, Long>> sortMap(
			Map<Long, Long> mapWordIDCount) {
		List<Map.Entry<Long, Long>> wordcountList = new ArrayList<Map.Entry<Long, Long>>(
				mapWordIDCount.entrySet());
		Collections.sort(wordcountList,
				new Comparator<Map.Entry<Long, Long>>() {
					public int compare(Map.Entry<Long, Long> o1,
							Map.Entry<Long, Long> o2) {
						return (int) (o1.getKey() - o2.getKey());
					}
				});
		return wordcountList;
	}

}
