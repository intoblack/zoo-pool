package com.weidou.mota.zoopool.util;

import java.util.Comparator;

public class StringComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		String str1 = (String) o1;
		String str2 = (String) o2;
		if (str1.equals(str2)) {
			return 0;
		} else {
			return str1.compareTo(str2);
		}
	}

}
