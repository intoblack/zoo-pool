package com.circus.liran.zoopool.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderUtils {

	/**
	 * 
	 * 对一个map进行排序
	 * 
	 * @param map
	 *            待排序map
	 * @param desc
	 *            是否降序排序
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortByValue(
			Map<K, V> map, final boolean desc) {
		if (map == null || map.isEmpty()) {
			return new ArrayList<Map.Entry<K, V>>();
		}
		List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(
				map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {

			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				if (desc) {
					if (o1.getValue() == null && o2.getValue() == null) {
						return 0;
					} else if (o1.getValue() == null) {
						return -1;
					} else if (o2.getValue() == null) {
						return 1;
					}
					return o1.getValue().compareTo(o2.getValue());

				} else {
					if (o1.getValue() == null && o2.getValue() == null) {
						return 0;
					} else if (o1.getValue() == null) {
						return 1;
					} else if (o2.getValue() == null) {
						return -1;
					}
					return o2.getValue().compareTo(o1.getValue());
				}
			}

		});

		return list;
	}

	/**
	 * 
	 * 对一个map进行排序 , 按照　key
	 * 
	 * @param map
	 *            待排序map
	 * @param desc
	 *            是否降序
	 * @return
	 */
	public static <K extends Comparable<? super K>, V> List<Map.Entry<K, V>> sortByKey(
			Map<K, V> map, final boolean desc) {
		if (map == null || map.isEmpty()) {
			return new ArrayList<Map.Entry<K, V>>();
		}
		List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(
				map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {

			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				if (desc) {
					if (o1.getKey() == null && o2.getKey() == null) {
						return 0;
					} else if (o1.getKey() == null) {
						return -1;
					} else if (o2.getKey() == null) {
						return 1;
					}
					return o1.getKey().compareTo(o2.getKey());

				} else {
					if (o1.getKey() == null && o2.getKey() == null) {
						return 0;
					} else if (o1.getKey() == null) {
						return 1;
					} else if (o2.getKey() == null) {
						return -1;
					}
					return o2.getKey().compareTo(o1.getKey());
				}
			}

		});

		return list;
	}

	/**
	 * 对一个collection 进行排序　，　好像似乎浪费内存了　
	 * 
	 * @param collection
	 *            待排序的collection
	 * @param desc
	 *            是否降序　
	 * @return
	 */
	public static <E extends Comparable<? super E>> List<E> sortCollections(
			Collection<E> collection, final boolean desc) {
		if (collection == null || collection.isEmpty()) {
			return new ArrayList<E>();
		}
		List<E> list = new ArrayList<E>();
		Collections.sort(list, new Comparator<E>() {

			@Override
			public int compare(E o1, E o2) {
				if (desc) {
					if (o1 == null && o2 == null) {
						return 0;
					} else if (o1 == null) {
						return -1;
					} else if (o2 == null) {
						return 1;
					}
					return o1.compareTo(o2);

				} else {
					if (o1 == null && o2 == null) {
						return 0;
					} else if (o1 == null) {
						return 1;
					} else if (o2 == null) {
						return -1;
					}
					return o2.compareTo(o1);
				}
			}

		});
		return list;
	}

	public static void main(String args[]) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("2", 1);
		map.put("4", null);
		map.put("5", -1);
		map.put(null, 100);
		map.put("1", 5);
		System.out.println(sortByKey(map, true));
		System.out.println(sortByValue(map, true));
	}

}
